package cpu.scheduler;

import cpu.process.Process;
import java.util.*;

public class RrScheduler implements Scheduler {

    private int quantum;

    public RrScheduler(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void schedule(List<Process> processes) {

        Queue<Process> q = new LinkedList<>();
        int time = 0;
        int completed = 0;

        while (completed < processes.size()) {

            for (Process p : processes) {
                if (p.getArrivalTime() == time) {
                    q.add(p);
                }
            }

            if (q.isEmpty()) {
                time++;
                continue;
            }

            Process p = q.poll();

            if (p.getResponseTime() == -1) {
                p.setResponseTime(time - p.getArrivalTime());
            }

            int exec = Math.min(quantum, p.getRemainingTime());
            time += exec;
            p.setRemainingTime(p.getRemainingTime() - exec);

            if (p.getRemainingTime() > 0) {
                q.add(p);
            } else {
                completed++;
                p.setCompletionTime(time);
                p.setTurnaroundTime(time - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
            }
        }
    }
}
