package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.analizers.Lexer;

public class SpaceLexer implements Lexer {
    public String[] getToken(String line) {
        line = line.replaceAll("[\"\']", "");
        return line.split(" ");
    }
}
