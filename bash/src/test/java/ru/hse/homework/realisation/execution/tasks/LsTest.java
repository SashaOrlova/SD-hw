package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.realisation.Environment;

import java.io.File;

import static org.junit.Assert.*;

public class LsTest {
    @Test
    public void test() throws Exception {
        Environment environment = new Environment();

        Ls lsTask = new Ls();
        String result = lsTask.execute(environment);

        assertEquals("bash.iml\n" +
                "target\n" +
                "pom.xml\n" +
                ".idea\n" +
                "src\n", result);
    }
}