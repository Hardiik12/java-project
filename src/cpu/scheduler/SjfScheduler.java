package cpu.scheduler;

import cpu.process.Process;
import java.util.*;

public class SjfScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {

        int time = 0, completed = 0;
        int n = processes.size();
        boolean[] done = new boolean[n];

        while (completed < n) {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                Process p = processes.get(i);
                if (!done[i] && p.getArrivalTime() <= time && p.getBurstTime() < minBurst) {
                    minBurst = p.getBurstTime();
                    idx = i;
                }
            }

            if (idx == -1) {
                time++;
                continue;
            }

            Process p = processes.get(idx);
            p.setResponseTime(time - p.getArrivalTime());
            time += p.getBurstTime();

            p.setCompletionTime(time);
            p.setTurnaroundTime(time - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());

            done[idx] = true;
            completed++;
        }
    }
}
