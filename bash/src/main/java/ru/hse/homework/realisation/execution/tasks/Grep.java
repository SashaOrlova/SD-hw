package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

public class Grep implements Task {
    public static final String COMMAND = "grep";
    private String[] args;

    @Override
    public void setArgs(String[] args) throws Exception {
        this.args = args;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    @Override
    public String[] getArgs() {
        return args;
    }
}
