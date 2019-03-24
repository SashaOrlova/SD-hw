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
        input = CliUtils.changeVars(input);

        ArrayList<String> splitByQuotes = splitByQuotes(input);

        ArrayList<String> splitByPipe = new ArrayList<>();
        for (String word: splitByQuotes) {
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

        return clearOutput(splitByEquals).toArray(new String[0]);
    }

    private List<String> clearOutput(ArrayList<String> input) {
        ArrayList<String> res = new ArrayList<>();
        for (String word: input) {
            if (!word.equals("")) {
                res.add(word);
            }
        }
        return res;
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
        ArrayDeque<Character> deque = new ArrayDeque<>();
        int position = 0;
        char quotes = ' ';
        do {
            if (input.charAt(position) == quotes && (input.charAt(position) == '"' || input.charAt(position) == '\'')) {
                deque.add(quotes);
                words.add(getStringFromDeque(deque));
                deque.clear();
                quotes = ' ';
                position++;
            } else if (' ' == quotes && (input.charAt(position) == '"' || input.charAt(position) == '\'')) {
                words.add(getStringFromDeque(deque));
                deque.clear();
                quotes = input.charAt(position);
                deque.add(quotes);
                position++;
            } else {
                deque.add(input.charAt(position));
                position++;
            }
        } while (position != input.length());

        if (quotes != ' ') {
            throw new spaceLexerException("Wrong quotes");
        }

        if (!deque.isEmpty()) {
            words.add(getStringFromDeque(deque));
        }

        return words;
    }

    private String getStringFromDeque(ArrayDeque<Character> deque) {
        StringBuilder answer = new StringBuilder();
        for (Character symbol: deque) {
            answer.append(symbol);
        }
        return answer.toString();
    }

    private class spaceLexerException extends Exception {
        spaceLexerException(String message) {
            super(message);
        }
    }
}
