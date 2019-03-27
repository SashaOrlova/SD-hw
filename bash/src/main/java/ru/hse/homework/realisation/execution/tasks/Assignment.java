package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.execution.Executor;

/**
 * Присваивание значения переменной
 */
public class Assignment implements Task {
    private String[] args;

    /**
     * Устанавливает аргументы команды
     * @param args arguments for command
     */
    @Override
    public void setArgs(String[] args) throws Exception {
        this.args = args;
    }

    /**
     * Выполняет задачу
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws Exception {
        if (this.args == null) {
            this.args = args;
        }
        if (this.args == null) {
            throw new AssignmentException("Undefine assignment value");
        }
        Executor.addToContext(this.args[0], this.args[1]);
        return null;
    }

    /**
     * Возвращает аргументы команды
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return args;
    }

    public static class AssignmentException extends Exception {
        AssignmentException(String message) {
            super(message);
        }
    }
}
