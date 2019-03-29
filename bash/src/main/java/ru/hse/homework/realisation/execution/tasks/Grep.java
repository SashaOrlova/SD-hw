package ru.hse.homework.realisation.execution.tasks;

import picocli.CommandLine;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.CliUtils;

import java.util.Arrays;

/**
 * Задача поиска по регулярному выражению
 */
public class Grep implements Task {
    public static final String COMMAND = "grep";
    private String[] args;
    private static String REGEX_FIND_WORD = "\\b%s\\b";

    @picocli.CommandLine.Option(names = "-i", description = "ignore case")
    private boolean ignoreCase = false;

    @picocli.CommandLine.Option(names = "-w", description = "only words")
    private boolean onlyWords = false;

    @picocli.CommandLine.Parameters(arity = "1..2" )
    private String[] regexps;

    @picocli.CommandLine.Option(names = "-A", description = "after lines")
    private int afterLines = 0;

    /**
     * Устанавливает аргументы команты
     * @param args arguments for command
     * @throws Exception если количество агрументов не верно
     */
    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length < 1) {
            throw new GrepException("Wrong args number");
        }
        this.args = args;
    }

    /**
     * Выполняет команду
     * @param args входной поток команды
     * @return результат выполнения
     * @throws Exception если произошла ошибка
     */
    @Override
    public String execute(String[] args) throws Exception {
        new CommandLine(this).parseArgs(this.args);

        String pattern;
        if (regexps == null || regexps.length < 1 || regexps.length > 2 || afterLines < 0) {
            throw new GrepException("Wrong args number");
        } else {
            pattern = regexps[0];
        }

        String text;
        if (args != null) {
            text = String.join(" ", args);
        } else {
            if (regexps.length < 2) {
                throw new GrepException("wrong args number");
            }
            text = CliUtils.getFile(regexps[1]);
        }

        if (ignoreCase) {
            pattern = "(?i)" + pattern;
        }
        if (onlyWords) {
            pattern = String.format(REGEX_FIND_WORD, pattern);
        }

        pattern = ".*" + pattern + ".*";
        String[] lines = text.split(System.lineSeparator());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches(pattern)) {
                if (afterLines > 0) {
                    result.append(System.lineSeparator())
                            .append(String.join(System.lineSeparator(),
                                    Arrays.copyOfRange(lines, i, Math.min(afterLines + i + 1, lines.length))));
                } else {
                    result.append(System.lineSeparator()).append(lines[i]);
                }
            }
        }
        return result.toString().trim();
    }

    private static class GrepException extends Exception {
        GrepException(String message) {
            super(message);
        }
    }

    /**
     * Получение аргументов команды
     * @return
     */
    @Override
    public String[] getArgs() {
        return args;
    }
}
