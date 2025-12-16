package cpu.util;

import cpu.process.Process;
import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<Process> readProcesses(String fileName) throws Exception {

        InputStream is = CSVReader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (is == null) {
            throw new FileNotFoundException("CSV file not found");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<Process> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");
            list.add(new Process(
                    p[0].trim(),
                    Integer.parseInt(p[1].trim()),
                    Integer.parseInt(p[2].trim())
            ));
        }
        return list;
    }
}
