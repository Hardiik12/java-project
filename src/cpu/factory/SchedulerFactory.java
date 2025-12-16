package cpu.factory;

import cpu.scheduler.*;

public class SchedulerFactory {

    public static Scheduler getScheduler(String policy, int quantum) {

        switch (policy.toUpperCase()) {
            case "SJF": return new SjfScheduler();
            case "SRTF": return new SrtfScheduler();
            case "RR": return new RrScheduler(quantum);
            default: return new FcfScheduler();
        }
    }
}
