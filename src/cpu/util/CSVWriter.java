package cpu.util;

import cpu.process.Process;
import java.io.*;
import java.util.List;

public class CSVWriter {

    public static void writeMetrics(List<Process> list, String path) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write("PID,WT,TAT,RT\n");

        for (Process p : list) {
            bw.write(p.getPid() + "," +
                    p.getWaitingTime() + "," +
                    p.getTurnaroundTime() + "," +
                    p.getResponseTime() + "\n");
        }
        bw.close();
    }
}
