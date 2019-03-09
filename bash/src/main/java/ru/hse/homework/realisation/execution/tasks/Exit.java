package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

public class Exit implements Task {
    public static final String COMMAND = "exit";

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length != 0)
            throw new ExitException("Wrong number of args in exit");
    }

    @Override
    public String execute() {
        System.exit(0);
        return null;
    }

    @Override
    public String[] getArgs() {
        return null;
    }

    public static class ExitException extends Exception {
        ExitException(String message) {
            super(message);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
