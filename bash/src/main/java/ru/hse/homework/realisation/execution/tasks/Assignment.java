package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.execution.Executor;

public class Assignment implements Task {
    String[] args;

    @Override
    public void setArgs(String[] args) throws Exception {
        this.args = args;
    }

    @Override
    public String execute() throws Exception {
        Executor.addToContext(args[0], args[1]);
        return "";
    }

    @Override
    public String[] getArgs() {
        return args;
    }
}
