package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.CliUtils;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
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
            arg = String.join(" ", args);
        } else {
            arg = CliUtils.getFile(this.args[0]);
        }
        BufferedReader reader = new BufferedReader(new StringReader(arg));
        int linesNum = 0;
        while (reader.readLine() != null) {
            linesNum++;
        }
        long wordsNum = Arrays.stream(
                arg.split("\\s"))
                .filter(x -> !"".equals(x))
                .count();
        int byteNum = arg.getBytes().length;
        return linesNum + "\t" + wordsNum + "\t" + byteNum;
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
