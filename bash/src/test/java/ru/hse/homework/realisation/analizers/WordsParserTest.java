package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.execution.tasks.Echo;
import ru.hse.homework.realisation.execution.tasks.Exit;
import ru.hse.homework.realisation.execution.tasks.Pwd;
import ru.hse.homework.realisation.execution.tasks.Wc;

import static org.junit.Assert.*;

public class WordsParserTest {

    @org.junit.Test
    public void smokeParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"echo", "lol"};
        Task[] res = parser.getTasks(args);
        Task testTask = new Echo();
        testTask.setArgs(new String[]{"lol"});
        assertEquals(testTask, res[0]);
    }

    @org.junit.Test
    public void wcParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"wc", "test"};
        Task[] res = parser.getTasks(args);
        Task testTask = new Wc();
        testTask.setArgs(new String[]{"test"});
        assertEquals(testTask, res[0]);
    }

    @org.junit.Test
    public void exitParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"exit"};
        Task[] res = parser.getTasks(args);
        Task testTask = new Exit();
        assertEquals(testTask, res[0]);
    }

    @org.junit.Test
    public void pipeParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"pwd","|", "exit"};
        Task[] res = parser.getTasks(args);
        Task exitTask = new Exit();
        Task pwdTask = new Pwd();
        assertArrayEquals(new Task[]{pwdTask, exitTask}, res);
    }

    @org.junit.Test
    public void assignmentParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args1 = {"var", "=", "\"string\""};
        String[] args2 = {"echo", "$var"};
        parser.getTasks(args1);
        Task[] res = parser.getTasks(args2);
        Task echoTask = new Echo();
        echoTask.setArgs(new String[]{"\"string\""});
        assertArrayEquals(new Task[]{echoTask}, res);
    }
}