package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.execution.Executor;

public class Assignment implements Task {
    private String[] args;

    /**
     * @param args arguments for command
     * @throws Exception
     */
    @Override
    public void setArgs(String[] args) throws Exception {
        this.args = args;
    }

    /**
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
