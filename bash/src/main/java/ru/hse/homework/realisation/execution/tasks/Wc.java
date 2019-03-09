package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.util.Arrays;

public class Wc implements Task {
    public static final String COMMAND = "wc";
    private static String[] args;

    @Override
    public void setArgs(String[] args) {
        Wc.args = args;
    }

    @Override
    public String execute() throws Exception {
        Task cat = new Cat();
        cat.setArgs(args);
        String arg = cat.execute();
        int linesNum = arg.split("[\n\r]").length;
        int wordsNum = arg.split(" ").length;
        int byteNum = arg.getBytes().length;
        return Integer.toString(linesNum) + ' ' + Integer.toString(wordsNum) + ' ' + Integer.toString(byteNum);
    }

    @Override
    public String[] getArgs() {
        return args;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.equals(args, ((Task) obj).getArgs());
    }
}
