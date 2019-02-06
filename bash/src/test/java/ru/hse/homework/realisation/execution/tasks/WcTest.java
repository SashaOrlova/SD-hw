package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;

import static org.junit.Assert.*;

public class WcTest {
    @Test
    public void simpleTest() throws Exception {
        Task wcTask = new Wc();
        wcTask.setArgs(new java.lang.String[]{"test test test\n line line line"});
        String res = wcTask.execute();
        assertEquals("2 6 30", res);
    }
}