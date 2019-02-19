package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.util.Arrays;

public class Wc implements Task {
    public static final String COMMAND = "wc";
    private static String arg;

    @Override
    public void setArgs(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg: args ) {
            stringBuilder.append(arg);
        }
        arg = stringBuilder.toString();
    }

    @Override
    public String execute(Environment environment) {
        int linesNum = arg.split("[\n\r]").length;
        int wordsNum = arg.split(" ").length;
        int byteNum = arg.getBytes().length;
        return Integer.toString(linesNum) + ' ' + Integer.toString(wordsNum) + ' ' + Integer.toString(byteNum);
    }

    @Override
    public String[] getArgs() {
        return new String[]{arg};
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.equals(new String[]{arg}, ((Task) obj).getArgs());
    }


    private static class WCException extends Exception {
        WCException(String message) {
            super(message);
        }
    }
}
