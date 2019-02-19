package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Cat implements Task {
    public static final String COMMAND = "cat";
    private String arg;

    public void setArgs(String[] args) throws CatException {
        if (args.length != 1)
            throw new CatException("Wrong number of args in cat");
        arg = args[0];
    }

    public String execute(Environment environment) throws IOException {
        char[] buf = new char[256];
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(arg)) {
            int c = reader.read(buf);
            while(c > 0){
                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                result.append(buf);
                c = reader.read(buf);
            }
        }
        return result.toString();
    }

    @Override
    public String[] getArgs() {
        return new String[]{arg};
    }

    private static class CatException extends Exception {
        CatException(String message) {
            super(message);
        }
    }
}
