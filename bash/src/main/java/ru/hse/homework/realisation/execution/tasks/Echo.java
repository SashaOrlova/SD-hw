package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.util.Arrays;

public class Echo implements Task {
    public static final String COMMAND = "echo";
    private String[] args = null;

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws EchoException {
        if (args != null) {
            this.args = args;
        }
        StringBuilder result = new StringBuilder();
        for (String arg: this.args) {
            if (arg.startsWith("\"") || arg.startsWith("'")) {
                arg = arg.substring(1, arg.length() - 1);
            }
            result.append(arg).append(' ');
        }
        return result.toString();
    }

    /**
     * @return аргументы команды
     */
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
