package cpu.scheduler;

import cpu.process.Process;
import java.util.List;

public class SrtfScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {

        int time = 0, completed = 0;
        int n = processes.size();

        while (completed < n) {
            Process shortest = null;

            for (Process p : processes) {
                if (p.getArrivalTime() <= time && p.getRemainingTime() > 0) {
                    if (shortest == null || p.getRemainingTime() < shortest.getRemainingTime()) {
                        shortest = p;
                    }
                }
            }

            if (shortest == null) {
                time++;
                continue;
            }

            if (shortest.getResponseTime() == -1) {
                shortest.setResponseTime(time - shortest.getArrivalTime());
            }

            shortest.setRemainingTime(shortest.getRemainingTime() - 1);
            time++;

            if (shortest.getRemainingTime() == 0) {
                completed++;
                shortest.setCompletionTime(time);
                shortest.setTurnaroundTime(time - shortest.getArrivalTime());
                shortest.setWaitingTime(shortest.getTurnaroundTime() - shortest.getBurstTime());
            }
        }
    }
}
