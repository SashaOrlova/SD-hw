package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;

import static org.junit.Assert.*;

public class GrepTest {
    @Test
    public void simpleTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"aaa", "aaa bbb"});
        String res = grep.execute(null);
        assertEquals("aaa", res);
    }

    @Test
    public void manyMatchesTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"a+", "a aa aaa bbb aaaa"});
        String res = grep.execute(null);
        assertEquals("a aa aaa aaaa", res);
    }

    @Test
    public void caseTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"i", "hey", "Hey HEY HeY"});
        String res = grep.execute(null);
        assertEquals("Hey HEY HeY", res);
    }

    @Test
    public void wordsTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"w", "hey", "hey world! heyworld"});
        String res = grep.execute(null);
        assertEquals("hey", res);
    }

    @Test
    public void linesTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"A", "2", "summer", "i wont summer\nmore\nmore\nand more"});
        String res = grep.execute(null);
        assertEquals("summer\nmore\nmore\n", res);
    }
}