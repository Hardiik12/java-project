package cpu.scheduler;

import cpu.process.Process;
import java.util.List;

public class FcfScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {
        int time = 0;

        for (Process p : processes) {
            if (time < p.getArrivalTime()) {
                time = p.getArrivalTime();
            }
            p.setResponseTime(time - p.getArrivalTime());
            time += p.getBurstTime();

            p.setCompletionTime(time);
            p.setTurnaroundTime(time - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
        }
    }
}
