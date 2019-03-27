package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

/**
 * Выход из программы
 */
public class Exit implements Task {
    public static final String COMMAND = "exit";

    /**
     * Устанавливает аргументы команды, они игнорируются
     * @param args arguments for command
     */
    @Override
    public void setArgs(String[] args) {
    }

    /**
     * Выполняет задачу
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws ExitException {
        throw new ExitException("Program exit");
    }

    /**
     * Возвращает аргументы команды
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return new String[0];
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
