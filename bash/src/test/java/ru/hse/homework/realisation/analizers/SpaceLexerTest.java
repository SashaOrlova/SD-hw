package ru.hse.homework.realisation.analizers;

import org.junit.Test;
import ru.hse.homework.interfaces.analizers.Lexer;

import static org.junit.Assert.*;

public class SpaceLexerTest {

    @Test
    public void smokeGetToken() throws Exception {
        Lexer lexer = new SpaceLexer();
        String[] res = lexer.getTokens("wc test words | pwd | exit");
        assertArrayEquals(new String[]{"wc", "test", "words", "|", "pwd", "|", "exit"}, res);
    }

    @Test
    public void quotesGetToken() throws Exception {
        Lexer lexer = new SpaceLexer();
        String[] res = lexer.getTokens("echo \"what if elephants can fly?\" | wc");
        assertArrayEquals(new String[]{"echo", "what if elephants can fly?", "|", "wc"}, res);
    }
}