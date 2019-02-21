package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.realisation.Environment;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class CdTest {
    @Test
    public void cdSomewhere() throws Exception {
        Environment environment = new Environment();
        String before = environment.getCurrentPath().toString();

        Ls lsTask = new Ls();
        lsTask.setArgs(new String[]{"src"});
        String result = lsTask.execute(environment);

        assertEquals("", result);
        assertEquals(before + File.separator + "src", environment.getCurrentPath().toString());
    }
}