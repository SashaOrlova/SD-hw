package ru.hse.homework.realisation.analizers;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.execution.tasks.Echo;
import ru.hse.homework.realisation.execution.tasks.Exit;
import ru.hse.homework.realisation.execution.tasks.Pwd;
import ru.hse.homework.realisation.execution.tasks.Wc;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WordsParserTest {

    @org.junit.Test
    public void smokeParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"echo", "lol"};
        Task res = parser.getOneTask(args);
        Task testTask = new Echo();
        testTask.setArgs(new String[]{"lol"});
        assertEquals(testTask, res);
    }

    @org.junit.Test
    public void wcParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"wc", "test"};
        Task res = parser.getOneTask(args);
        Task testTask = new Wc();
        testTask.setArgs(new String[]{"test"});
        assertEquals(testTask, res);
    }

    @org.junit.Test
    public void exitParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"exit"};
        Task res = parser.getOneTask(args);
        Task testTask = new Exit();
        assertEquals(testTask, res);
    }

    @org.junit.Test
    public void pipeParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"pwd","|", "exit"};
        Task res = parser.getOneTask(args);
        Task exitTask = new Exit();
        Task pwdTask = new Pwd();
        assertEquals(pwdTask, res);
        res = parser.getOneTask(Arrays.copyOfRange(args, 2, args.length));
        assertEquals(exitTask, res);
    }

    @org.junit.Test
    public void assignmentParser() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args1 = {"var", "=", "\"string\""};
        String[] args2 = {"echo", "$var"};
        Task res = parser.getOneTask(args1);
        res.execute(null);
        res = parser.getOneTask(args2);
        Task echoTask = new Echo();
        echoTask.setArgs(new String[]{"string"});
        assertEquals(echoTask, res);
    }

    @Test
    public void getOneTask() throws Exception {
        WordsParser parser = new WordsParser();
        String[] args = {"wc", "test"};
        String[] argsEcho = {"echo", "test1", "test2", "test3"};

        Task res = parser.getOneTask(args);
        Task resEcho = parser.getOneTask(argsEcho);

        Task testTask = new Wc();
        testTask.setArgs(new String[]{"test"});
        Task echoTask = new Echo();
        echoTask.setArgs(new String[]{"test1", "test2", "test3"});

        assertEquals(testTask, res);
        assertEquals(echoTask, resEcho);
    }
}