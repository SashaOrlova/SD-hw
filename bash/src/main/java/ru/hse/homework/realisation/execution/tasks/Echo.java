package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.util.Arrays;

public class Echo implements Task {
    public static final String COMMAND = "echo";
    private String[] args;

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length == 0)
            throw new EchoException("Wrong number of args in echo");
        this.args = args;
    }

    @Override
    public String execute(Environment environment) throws Exception {
        StringBuilder result = new StringBuilder();
        for (String arg: args) {
            result.append(arg);
        }
        return result.toString();
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    public static class EchoException extends Exception {
        EchoException(String message) {
            super(message);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.equals(args, ((Task) obj).getArgs());
    }
}
