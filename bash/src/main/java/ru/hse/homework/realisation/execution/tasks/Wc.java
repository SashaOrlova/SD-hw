package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.CliUtils;

import java.util.Arrays;

public class Wc implements Task {
    public static final String COMMAND = "wc";
    private String[] args;

    /**
     * @param args arguments for command
     */
    @Override
    public void setArgs(String[] args) throws WcException {
        if (this.args != null && this.args.length > 1) {
            throw new WcException("Wrong args number");
        }
        this.args = args;
    }

    /**
     * @param args входной поток
     * @return результат выполнения
     * @throws Exception
     */
    @Override
    public String execute(String[] args) throws Exception {
        String arg;
        if (args != null) {
            StringBuilder argBuilder = new StringBuilder();
            for (String word: args) {
                argBuilder.append(word).append(' ');
            }
            arg = argBuilder.toString();
        } else {
            arg = CliUtils.getFile(this.args[0]);
        }
        int linesNum = arg.replaceAll("[\n\r]{2,}", " ").split("[\n\r]").length;
        int wordsNum = arg.replaceAll(" {2,}", " ").split(" ").length;
        int byteNum = arg.getBytes().length;
        return Integer.toString(linesNum) + ' ' + Integer.toString(wordsNum) + ' ' + Integer.toString(byteNum);
    }

    /**
     * @return аргументы команды
     */
    @Override
    public String[] getArgs() {
        return args;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.equals(args, ((Task) obj).getArgs());
    }

    private static class WcException extends Exception {
        WcException(String message) {
            super(message);
        }
    }
}
