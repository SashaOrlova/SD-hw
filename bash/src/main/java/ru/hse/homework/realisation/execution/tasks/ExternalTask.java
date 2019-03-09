package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExternalTask implements Task {
    private String[] command;

    @Override
    public void setArgs(String[] args) {
        command = args;
    }

    @Override
    public String execute() throws Exception {
        Process p = Runtime.getRuntime().exec(String.join(" ", command));
        p.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine())!= null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }

    @Override
    public String[] getArgs() {
        return command;
    }
}
