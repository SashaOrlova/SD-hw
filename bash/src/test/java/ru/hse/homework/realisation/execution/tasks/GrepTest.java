package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;

import static org.junit.Assert.*;

public class GrepTest {
    @Test
    public void simpleTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"aaa"});
        String res = grep.execute(new String[]{"aaa\n bbb"});
        assertEquals("aaa", res);
    }

    @Test
    public void manyMatchesTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"a+"});
        String res = grep.execute(new String[]{"a\naa\naaa\nbbb\naaaa"});
        assertEquals("a\naa\naaa\naaaa", res);
    }

    @Test
    public void caseTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"-i", "hey"});
        String res = grep.execute(new String[]{"Hey\nHEY\nHeY"});
        assertEquals("Hey\nHEY\nHeY", res);
    }

    @Test
    public void wordsTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"-w", "hey"});
        String res = grep.execute(new String[]{"hey world!\nheyworld"});
        assertEquals("hey world!", res);
    }

    @Test
    public void linesTest() throws Exception {
        Task grep = new Grep();
        grep.setArgs(new String[]{"-A", "2", "summer", "i wont summer\nmore\nmore\nand more"});
        String res = grep.execute(new String[]{"i wont\nsummer\nmore\nmore\nand more"});
        assertEquals("summer\nmore\nmore", res);
    }
}