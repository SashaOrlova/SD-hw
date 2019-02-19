package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cd implements Task {
    public static final String COMMAND = "cd";
    private String[] args;

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length > 1) {
            throw new CdException("Wrong number of args in cd");
        }
        this.args = args;
    }

    @Override
    public String execute(Environment environment) throws Exception {
        if (args.length > 1) {
            Path homePath = Paths.get(System.getProperty("user.home")).toAbsolutePath();
            environment.setCurrentPath(
                    homePath
            );
            return "";
        } else {
            Path newPath = Paths.get(args[0]).resolve(environment.getCurrentPath());
            if (Files.exists(newPath)) {
                environment.setCurrentPath(newPath);
                return "";
            } else {
                return String.format("%s: %s: No such file or directory", COMMAND, args[0]);
            }
        }
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    public static class CdException extends Exception {
        CdException(String message) {
            super(message);
        }
    }
}
