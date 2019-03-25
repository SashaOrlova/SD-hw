package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.CliUtils;

import java.io.IOException;

public class Cat implements Task {
    public static final String COMMAND = "cat";
    private String arg;

    public void setArgs(String[] args) throws CatException {
        if (args.length > 0) {
            arg = args[0];
        }
        if (args.length > 1) {
            throw new CatException("Wrong args number");
        }
    }

    /**
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    public String execute(String[] args) throws IOException, CatException {
        if (this.arg != null) {
            return CliUtils.getFile(arg);
        } else if (args != null && args.length > 0) {
            return  String.join(" ", args);
        } else {
            throw new CatException("Wrong input argument");
        }
    }

    /**
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return new String[]{arg};
    }

    private static class CatException extends Exception {
        CatException(String message) {
            super(message);
        }
    }
}
