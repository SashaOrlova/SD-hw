package ru.hse.homework.realisation;

import ru.hse.homework.realisation.execution.Executor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class CliUtils {
    public static String getFile(String filename) throws IOException {
        {
            char[] buf = new char[256];
            StringBuilder result = new StringBuilder();
            try (FileReader reader = new FileReader(filename)) {
                int c = reader.read(buf);
                while (c > 0) {
                    if (c < 256) {
                        buf = Arrays.copyOf(buf, c);
                    }
                    result.append(buf);
                    c = reader.read(buf);
                }
            }
            return result.toString();
        }
    }

    public static String changeVars(String word) {
        for (Object o : Executor.getVariables().entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            word = word.replaceAll("\\$" + pair.getKey(), (String) pair.getValue());
        }
        return word;
    }
}
