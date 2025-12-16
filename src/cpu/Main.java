package cpu;

import cpu.process.Process;
import cpu.scheduler.Scheduler;
import cpu.factory.SchedulerFactory;
import cpu.util.CSVReader;
import cpu.util.CSVWriter;
import cpu.util.Logger;

import java.util.*;
import java.io.File;


/**
 * CPU Scheduling & CPU Burst Analyzer
 * CLI based simulator
 */
public class Main {

    private static List<Process> processes = new ArrayList<>();
    private static Scheduler scheduler = null;
    private static String policy = "FCFS";
    private static int quantum = 2; // default quantum for RR

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Logger.info("CPU Scheduler Simulator started");

        while (true) {
            System.out.print("cmd> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String command = parts[0].toLowerCase();

            try {
                switch (command) {

                    case "load":
                        processes = CSVReader.readProcesses("processes.csv");
                        System.out.println("Loaded " + processes.size() + " processes.");
                        break;

                    case "set":
                        if (parts.length < 3 || !parts[1].equalsIgnoreCase("policy")) {
                            System.out.println("Usage: set policy <FCFS|SJF|SRTF|RR> [--quantum q]");
                            break;
                        }

                        policy = parts[2].toUpperCase();

                        if (policy.equals("RR") && line.contains("--quantum")) {
                            String[] qParts = line.split("--quantum");
                            quantum = Integer.parseInt(qParts[1].trim());
                        }

                        scheduler = SchedulerFactory.getScheduler(policy, quantum);
                        System.out.println("Policy set to " + policy +
                                (policy.equals("RR") ? " (quantum=" + quantum + ")" : ""));
                        break;

                    case "simulate":
                        if (processes.isEmpty()) {
                            System.out.println("No processes loaded.");
                            break;
                        }
                        if (scheduler == null) {
                            scheduler = SchedulerFactory.getScheduler(policy, quantum);
                        }

                        scheduler.schedule(processes);
                        System.out.println("Simulation completed.");
                        break;

                    case "metrics":
                        printMetrics(processes);
                        break;

                    case "export":
                        ensureOutputFolder();   // ← ADD THIS LINE
                        CSVWriter.writeMetrics(processes, "output/metrics.csv");
                        System.out.println("Metrics exported to output/metrics.csv");
                        break;

                    case "help":
                        printHelp();
                        break;

                    case "exit":
                        System.out.println("Exiting simulator.");
                        System.exit(0);

                    default:
                        System.out.println("Unknown command. Type 'help'.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println(" load processes.csv");
        System.out.println("    -> loads process data from CSV file");
        System.out.println();
        System.out.println(" set policy <FCFS|SJF|SRTF|RR> [--quantum q]");
        System.out.println("    -> selects CPU scheduling algorithm");
        System.out.println("    Examples:");
        System.out.println("      set policy FCFS");
        System.out.println("      set policy SJF");
        System.out.println("      set policy SRTF");
        System.out.println("      set policy RR --quantum 2");
        System.out.println();
        System.out.println(" simulate");
        System.out.println("    -> runs the scheduling simulation");
        System.out.println();
        System.out.println(" metrics");
        System.out.println("    -> displays waiting time, turnaround time, response time");
        System.out.println();
        System.out.println(" export");
        System.out.println("    -> exports metrics to output/metrics.csv");
        System.out.println();
        System.out.println(" exit");
        System.out.println("    -> exits the simulator");
    }


    private static void printMetrics(List<Process> list) {

        System.out.println("PID\tAT\tBT\tCT\tWT\tTAT\tRT");

        for (Process p : list) {
            System.out.println(
                    p.getPid() + "\t" +
                    p.getArrivalTime() + "\t" +
                    p.getBurstTime() + "\t" +
                    p.getCompletionTime() + "\t" +
                    p.getWaitingTime() + "\t" +
                    p.getTurnaroundTime() + "\t" +
                    p.getResponseTime()
            );
        }

        double avgWT = list.stream().mapToInt(Process::getWaitingTime).average().orElse(0);
        double avgTAT = list.stream().mapToInt(Process::getTurnaroundTime).average().orElse(0);
        double avgRT = list.stream().mapToInt(Process::getResponseTime).average().orElse(0);

        System.out.printf("Averages -> WT: %.2f, TAT: %.2f, RT: %.2f%n",
                avgWT, avgTAT, avgRT);
    }
    private static void ensureOutputFolder() {
        File out = new File("output");
        if (!out.exists()) {
            out.mkdirs();
        }
    }
}
