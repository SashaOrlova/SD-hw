package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.io.File;

public class Pwd implements Task {
    public static final String COMMAND = "pwd";

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length != 0)
            throw new PwdException("Wrong number of args in pwd");
    }

    @Override
    public String execute() throws Exception {
        return new File("").getAbsoluteFile().getAbsolutePath();
    }

    @Override
    public String[] getArgs() {
        return null;
    }

    private static class PwdException extends Exception {
        PwdException(String message) {
            super(message);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
