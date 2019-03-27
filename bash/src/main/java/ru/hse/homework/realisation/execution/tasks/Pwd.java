package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.io.File;

/**
 * Вывод текущей директории
 */
public class Pwd implements Task {
    public static final String COMMAND = "pwd";

    /**
     * Устанавливает аргументы команды
     * @param args arguments for command
     * @throws Exception если аргументы имеются
     */
    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length != 0)
            throw new PwdException("Wrong number of args in pwd");
    }

    /**
     * Выполняет задачу
     * @param args входной поток
     * @return результат выполнения
     */
    @Override
    public String execute(String[] args) {
        return new File("").getAbsoluteFile().getAbsolutePath();
    }

    /**
     * Возвращает аргументы команды
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return new String[0];
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
