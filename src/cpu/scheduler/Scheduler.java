package cpu.scheduler;

import cpu.process.Process;
import java.util.List;

public interface Scheduler {
    void schedule(List<Process> processes);
}
