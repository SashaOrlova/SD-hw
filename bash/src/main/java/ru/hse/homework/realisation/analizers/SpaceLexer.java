package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.analizers.Lexer;

import java.util.ArrayList;
import java.util.Stack;

public class SpaceLexer implements Lexer {
    public String[] getToken(String line) throws spaceLexerException {
        line = line.replaceAll("\"", " \" ");
        line = line.replaceAll("\'", " \' ");
        line = line.replaceAll(" {2,}", " ");
        String[] lines = line.split(" ");
        ArrayList<String> resultLines = new ArrayList<>();
        Stack<String> wordsStack = new Stack<>();
        int quotesBalance = 0;
        int doubleQuotesBalance = 0;

        for (String word: lines) {
            if ("\"".equals(word)) {
                if (doubleQuotesBalance == 0) {
                    doubleQuotesBalance++;
                    wordsStack.push(word);
                } else {
                    StringBuilder bigWord = new StringBuilder();
                    while (!wordsStack.peek().equals("\"") || !wordsStack.peek().equals("\"")) {
                        bigWord.insert(0, wordsStack.pop() + " ");
                    }
                    if (wordsStack.pop().equals("\'")) {
                        throw new spaceLexerException("Wrong quotes");
                    }
                    bigWord.deleteCharAt(bigWord.length() - 1);
                    resultLines.add(bigWord.toString());
                    doubleQuotesBalance--;
                }
                continue;
            }
            if ("\'".equals(word)) {
                if (quotesBalance == 0) {
                    quotesBalance++;
                    wordsStack.push(word);
                } else {
                    StringBuilder bigWord = new StringBuilder();
                    while (!wordsStack.peek().equals("\"") || !wordsStack.peek().equals("\"")) {
                        bigWord.insert(0, wordsStack.pop() + " ");
                    }
                    if (wordsStack.pop().equals("\"")) {
                        throw new spaceLexerException("Wrong quotes");
                    }
                    bigWord.deleteCharAt(bigWord.length() - 1);
                    resultLines.add(bigWord.toString());
                    quotesBalance--;
                }
                continue;
            }
            if (quotesBalance == 1 || doubleQuotesBalance == 1) {
                wordsStack.push(word);
            } else {
                resultLines.add(word);
            }
        }
        if (!wordsStack.empty()) {
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
