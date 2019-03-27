package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.analizers.Lexer;
import ru.hse.homework.realisation.CliUtils;

import java.util.*;


/**
 * Разбивает входной поток на токены для дальнейшей обработки
 */
public class SpaceLexer implements Lexer {

    /**
     * Разбивает строку на токены
     *
     * @param input - строка, которую разбиваем на токены
     * @return упорядоченный массив получившихся после разбиения строки на токены
     * @throws spaceLexerException - если произошла ошибка разбиения на токены
     */
    @Override
    public String[] getTokens(String input) throws spaceLexerException {
        ArrayList<String> splitByQuotes = splitByQuotes(input);

        ArrayList<String> withChangesVars = new ArrayList<>();
        for (String word: splitByQuotes) {
            if (!word.startsWith("'")) {
                withChangesVars.add(CliUtils.changeVars(word));
            } else {
                withChangesVars.add(word);
            }
        }

        ArrayList<String> splitByPipe = new ArrayList<>();
        for (String word: withChangesVars) {
            splitByPipe.addAll(splitByPipe(word));
        }

        ArrayList<String> splitByWhitespaces = new ArrayList<>();
        for (String word: splitByPipe) {
            splitByWhitespaces.addAll(splitByWhiteSpaces(word));
        }

        ArrayList<String> splitByEquals = new ArrayList<>();
        for (String word: splitByWhitespaces) {
            splitByEquals.addAll(splitByEquals(word));
        }

        splitByEquals.removeIf(""::equals);
        return splitByEquals.toArray(new String[0]);
    }

    /**
     * Разбивает строку, ориентируясь на равенство
     * не трогает строки, окруженные кавычками
     *
     * @param input - строка, которую разбиваем с помощью =
     * @return упорядоченный массив получившихся после разбиения строк
     */
    public List<String> splitByEquals(String input) {
        if (input.startsWith("\"") || input.startsWith("'")) {
            return Collections.singletonList(input);
        }
        return Arrays.asList(input.split("((?<==)|(?==))"));
    }

    /**
     * Разбивает строку, ориентируясь на пайп
     * не трогает строки, окруженные кавычками
     *
     * @param input - строка, которую разбиваем с помощью =
     * @return упорядоченный массив получившихся после разбиения строк
     */
    public List<String> splitByPipe(String input) {
        if (input.startsWith("\"") || input.startsWith("'")) {
            return Collections.singletonList(input);
        }
        return Arrays.asList(input.split("((?<=\\|)|(?=\\|))"));
    }

    /**
     * Разбивает строку ориентируясь на пробелы,
     * удаляет не нужные пробелы,
     * не трогает строки, окруженные кавычками
     *
     * @param input - строка, которую разбиваем с помощью ' '
     * @return упорядоченный массив получившихся после разбиения строк
     */
    public List<String> splitByWhiteSpaces(String input) {
        if (input.startsWith("\"") || input.startsWith("'")) {
            return Collections.singletonList(input);
        }
        input = input.replaceAll(" {2,}", " ");
        input = input.trim();
        return Arrays.asList(input.split(" "));
    }

    /**
     * Разбивает строку с помощью кавычек
     * Пример: test "test1'test2" test3 -> [test, "test1'test2", test3]
     *
     * @param input - строка, которую разбиваем с помощью " и '
     * @return упорядоченный массив получившихся после разбиения строк
     * @throws spaceLexerException если последовательность кавычек не верная
     */
    public ArrayList<String> splitByQuotes(String input) throws spaceLexerException {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        char quotes = ' ';
        for (char c : input.toCharArray()) {
            if (c == quotes && (c == '"' || c == '\'')) {
                stringBuilder.append(quotes);
                words.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                quotes = ' ';
            } else if (' ' == quotes && (c == '"' || c == '\'')) {
                words.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                quotes = c;
                stringBuilder.append(quotes);
            } else {
                stringBuilder.append(c);
            }
        }

        if (quotes != ' ') {
            throw new spaceLexerException("Wrong quotes");
        }

        if (stringBuilder.length() != 0) {
            words.add(stringBuilder.toString());
        }

        return words;
    }

    private class spaceLexerException extends Exception {
        spaceLexerException(String message) {
            super(message);
        }
    }
}
