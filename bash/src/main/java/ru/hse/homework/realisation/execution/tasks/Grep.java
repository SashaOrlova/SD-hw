package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep implements Task {
    public static final String COMMAND = "grep";
    private String[] args;
    public static String REGEX_FIND_WORD = "\\b%s\\b";

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length < 2) {
            throw new Exception();
        }
        this.args = args;
    }

    @Override
    public String execute() throws Exception {
        String pattern = args[args.length - 2];
        String text = args[args.length - 1];
        if (Arrays.asList(args).contains("i")) {
            pattern = "(?i)" + pattern;
        }
        if (Arrays.asList(args).contains("w")) {
            pattern = String.format(REGEX_FIND_WORD, Pattern.quote(pattern));
        }
        if (Arrays.asList(args).contains("A")) {
            int num = Integer.parseInt(args[Arrays.asList(args).indexOf("A") + 1]);
            pattern = pattern + "(.*\n){" + Integer.toString(num + 1) + "}";
        }
        Pattern regexPattern = Pattern.compile(pattern);
        StringBuilder res = new StringBuilder();
        Matcher m = regexPattern.matcher(text);
        while (m.find()) {
            res.append(' ');
            res.append(m.group());
        }
        res.deleteCharAt(0);
        return res.toString();
    }

    @Override
    public String[] getArgs() {
        return args;
    }
}
