# CPU Scheduling & CPU Burst Analyzer

A Java-based Command Line Interface (CLI) simulator for simulating, analyzing, and evaluating various CPU scheduling algorithms and process burst metrics.

---

## 📌 Features

- **Multiple CPU Scheduling Algorithms Supported:**
  - **FCFS** (First-Come, First-Served) - Non-preemptive
  - **SJF** (Shortest Job First) - Non-preemptive
  - **SRTF** (Shortest Remaining Time First) - Preemptive SJF
  - **RR** (Round Robin) - Preemptive with customizable time quantum
- **CSV Data Ingestion:** Load process definitions directly from CSV files (`processes.csv`).
- **Detailed Performance Metrics:** Computes per-process and average values for:
  - Completion Time (CT)
  - Waiting Time (WT)
  - Turnaround Time (TAT)
  - Response Time (RT)
- **Metrics Export:** Export simulation results and metrics to `output/metrics.csv`.
- **Robust Exception Handling:** Validates process input for negative burst times, overlapping arrival constraints, and invalid time quanta.

---

## 📁 Project Structure

```text
CPU-Scheduling-Burst-Analyzer/
├── src/
│   └── cpu/
│       ├── Main.java                 # Interactive CLI Entry Point
│       ├── process/
│       │   └── Process.java          # Process Model & State
│       ├── scheduler/
│       │   ├── Scheduler.java        # Abstract Scheduler Interface / Class
│       │   ├── FcfScheduler.java     # FCFS Algorithm Implementation
│       │   ├── SjfScheduler.java     # SJF Algorithm Implementation
│       │   ├── SrtfScheduler.java    # SRTF Algorithm Implementation
│       │   └── RrScheduler.java      # Round Robin Algorithm Implementation
│       ├── factory/
│       │   └── SchedulerFactory.java # Factory for Scheduler Instantiation
│       ├── util/
│       │   ├── CSVReader.java        # CSV Parser for Process Input
│       │   ├── CSVWriter.java        # CSV Exporter for Process Metrics
│       │   └── Logger.java           # Logging Utility
│       └── exception/
│           ├── InvalidQuantumException.java
│           ├── NegativeBurstException.java
│           └── OverlappingArrivalException.java
├── resources/
│   └── processes.csv                 # Sample Process Data
├── output/                           # Exported Results Directory
└── README.md                         # Documentation
```

---

## ⚙️ Requirements

- **Java SE Development Kit (JDK) 8** or higher
- Terminal / Command Prompt

---

## 🚀 How to Build and Run

### 1. Compile the Code

From the root directory of the repository:

```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
```

### 2. Run the Application

```bash
java -cp bin cpu.Main
```

---

## 🖥️ Interactive CLI Commands

Once the simulator starts (`cmd>`), you can use the following commands:

| Command | Description | Example / Syntax |
| :--- | :--- | :--- |
| `load` | Load process definitions from `processes.csv` | `load` |
| `set policy` | Select scheduling algorithm & quantum | `set policy FCFS`<br>`set policy SJF`<br>`set policy SRTF`<br>`set policy RR --quantum 2` |
| `simulate` | Run the selected scheduling simulation | `simulate` |
| `metrics` | Display calculation table and average metrics | `metrics` |
| `export` | Save results to `output/metrics.csv` | `export` |
| `help` | Display command help documentation | `help` |
| `exit` | Exit the simulator | `exit` |

---

## 📊 CSV File Formats

### Input Format (`processes.csv`)

Each line represents a process in comma-separated format: `PID,ArrivalTime,BurstTime`

```csv
P1,0,5
P2,1,3
P3,2,8
P4,3,6
```

### Output Format (`output/metrics.csv`)

Exported output generates process metrics including:

```csv
PID,ArrivalTime,BurstTime,CompletionTime,WaitingTime,TurnaroundTime,ResponseTime
P1,0,5,5,0,5,0
P2,1,3,8,4,7,4
...
```

---

## 📄 License

This project is open source and available for educational and analytical purposes.
