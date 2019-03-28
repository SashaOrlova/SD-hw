package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;
import static org.junit.Assert.*;

public class EchoTest {
    @Test
    public void simpleTest() throws Exception {
        Task echoTask = new Echo();
        echoTask.setArgs(new String[]{"hello", "world", "!"});
        String res = echoTask.execute(null);
        assertEquals("hello world !", res);
    }
}