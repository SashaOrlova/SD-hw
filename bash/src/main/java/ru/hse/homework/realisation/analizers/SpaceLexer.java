package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.analizers.Lexer;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static java.lang.Math.max;

public class SpaceLexer implements Lexer {
    public String[] getTokens(String line) throws spaceLexerException {
        line = line.replaceAll("\"", " \" ");
        line = line.replaceAll("\'", " \' ");
        line = line.replaceAll("=", " = ");
        line = line.replaceAll(" {2,}", " ");
        String[] lines = line.split(" ");
        ArrayList<String> resultLines = new ArrayList<>();
        ArrayDeque<String> wordsStack = new ArrayDeque<>();
        int quotesBalance = 0;
        int doubleQuotesBalance = 0;

        for (String word: lines) {
            if ("\"".equals(word)) {
                if (doubleQuotesBalance == 0) {
                    doubleQuotesBalance++;
                    wordsStack.addLast(word);
                } else {
                    StringBuilder bigWord = new StringBuilder();
                    while (!wordsStack.getLast().equals("\"") || !wordsStack.getLast().equals("\"")) {
                        bigWord.insert(0, wordsStack.removeLast() + " ");
                    }
                    if (wordsStack.getLast().equals("\'")) {
                        throw new spaceLexerException("Wrong quotes");
                    }
                    wordsStack.removeLast();
                    bigWord.deleteCharAt(max(bigWord.length() - 1, 0));
                    resultLines.add(bigWord.toString());
                    doubleQuotesBalance--;
                }
                continue;
            }
            if ("\'".equals(word)) {
                if (quotesBalance == 0) {
                    quotesBalance++;
                    wordsStack.addLast(word);
                } else {
                    StringBuilder bigWord = new StringBuilder();
                    while (!wordsStack.getLast().equals("\"") || !wordsStack.getLast().equals("\"")) {
                        bigWord.insert(0, wordsStack.pop() + " ");
                    }
                    if (wordsStack.getLast().equals("\"")) {
                        throw new spaceLexerException("Wrong quotes");
                    }
                    bigWord.deleteCharAt(bigWord.length() - 1);
                    resultLines.add(bigWord.toString());
                    quotesBalance--;
                }
                continue;
            }
            if (quotesBalance == 1 || doubleQuotesBalance == 1) {
                wordsStack.add(word);
            } else {
                resultLines.add(word);
            }
        }
        if (!wordsStack.isEmpty()) {
            throw new spaceLexerException("Wrong number of quotes");
        }

        String[] resultLinesStrings = new String[resultLines.size()];
        return resultLines.toArray(resultLinesStrings);
    }

    private class spaceLexerException extends Exception {
        spaceLexerException(String message) {
            super(message);
        }
    }
}
