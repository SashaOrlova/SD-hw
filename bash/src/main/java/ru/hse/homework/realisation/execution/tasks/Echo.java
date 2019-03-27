package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.util.Arrays;

/**
 * Вывод входных аргументов
 */
public class Echo implements Task {
    public static final String COMMAND = "echo";
    private String[] args = null;

    /**
     * Устанавливает аргументы команды
     * @param args arguments for command
     */
    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Выполняет задачу
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws EchoException {
        if (args != null) {
            this.args = args;
        }
        return String.join(" ", this.args);
    }

    /**
     * Возвращает аргументы команды
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
