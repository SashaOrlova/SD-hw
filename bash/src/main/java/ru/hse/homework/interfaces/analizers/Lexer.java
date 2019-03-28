package ru.hse.homework.interfaces.analizers;

/**
 * Class for split bash input into tokens
 */
public interface Lexer {
    /**
     * Split line into tokens
     * @param line input line
     * @return tokens
     * @throws Exception
     */
    String[] getTokens(String line) throws Exception;
}
