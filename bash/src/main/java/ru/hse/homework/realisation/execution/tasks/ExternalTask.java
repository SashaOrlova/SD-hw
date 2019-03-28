package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Задача, выполняемая в bash
 */
public class ExternalTask implements Task {
    private String[] command;

    /**
     * Устанавливает аргументы команды
     * @param args arguments for command
     */
    @Override
    public void setArgs(String[] args) {
        command = args;
    }

    /**
     * Выполняет задачу
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec(String.join(" ", command));
        p.waitFor();
        if (p.exitValue() == 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            throw new ExternalException(output.toString());
        }
    }


    /**
     * Возвращает аргументы команды
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return command;
    }

    private static class ExternalException extends Exception {
        ExternalException(String message) {
            super(message);
        }
    }
}
