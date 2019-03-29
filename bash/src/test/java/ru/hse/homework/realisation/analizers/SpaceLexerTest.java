package ru.hse.homework.realisation.analizers;

import org.junit.Test;
import ru.hse.homework.interfaces.analizers.Lexer;

import java.util.Arrays;
import java.util.Collections;

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
        assertArrayEquals(new String[]{"echo", "\"what if elephants can fly?\"", "|", "wc"}, res);
    }

    @Test
    public void splitByQuotes() throws Exception {
        String test1 = "test \"test1'test2\" test3";
        String test2 = "test test3";
        String test3 = "test \"test1'test2\"";
        SpaceLexer lexer = new SpaceLexer();
        assertEquals(Arrays.asList("test ", "\"test1'test2\"", " test3"), lexer.splitByQuotes(test1));
        assertEquals(Collections.singletonList("test test3"), lexer.splitByQuotes(test2));
        assertEquals(Arrays.asList("test ", "\"test1'test2\""), lexer.splitByQuotes(test3));
    }

    @Test
    public void splitByWhitespace() {
        String test1 = "echo                   ";
        String test2 = "    cat   file.txt     ";
        SpaceLexer lexer = new SpaceLexer();
        assertEquals(Collections.singletonList("echo"), lexer.splitByWhiteSpaces(test1));
        assertEquals(Arrays.asList("cat", "file.txt"), lexer.splitByWhiteSpaces(test2));
    }

    @Test
    public void splitByPipe() {
        String test1 = "echo test | cat";
        String test2 = "cat test.txt | wc";
        String test3 = "| wc";
        SpaceLexer lexer = new SpaceLexer();
        assertEquals(Arrays.asList("echo test ", "|", " cat"), lexer.splitByPipe(test1));
        assertEquals(Arrays.asList("cat test.txt ", "|", " wc"), lexer.splitByPipe(test2));
        assertEquals(Arrays.asList("|", " wc"), lexer.splitByPipe(test3));
    }

    @Test
    public void splitByEquals() {
        String test1 = "e=\"test\"";
        String test2 = "t=test";
        SpaceLexer lexer = new SpaceLexer();
        assertEquals(Arrays.asList("e", "=", "\"test\""), lexer.splitByEquals(test1));
        assertEquals(Arrays.asList("t", "=", "test"), lexer.splitByEquals(test2));
    }
}