package cpu.process;

public class Process {

    private String pid;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;

    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;
    private int responseTime = -1;

    public Process(String pid, int arrival, int burst) {
        this.pid = pid;
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.remainingTime = burst;
    }

    public String getPid() { return pid; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public void setRemainingTime(int rt) { this.remainingTime = rt; }

    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int ct) { this.completionTime = ct; }

    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int wt) { this.waitingTime = wt; }

    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int tat) { this.turnaroundTime = tat; }

    public int getResponseTime() { return responseTime; }
    public void setResponseTime(int rt) { this.responseTime = rt; }
}
