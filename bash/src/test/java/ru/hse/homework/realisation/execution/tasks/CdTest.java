package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.realisation.Environment;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class CdTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void cdSomewhere() throws Exception {
        Environment environment = new Environment();
        environment.setCurrentPath(folder.getRoot().toPath());

        File innerFolder = folder.newFolder("innerFolder");

        Cd cdTask = new Cd();
        cdTask.setArgs(new String[]{"innerFolder"});
        String result = cdTask.execute(environment);

        assertEquals("", result);
        assertEquals(innerFolder.getAbsolutePath(),
                environment.getCurrentPath().toAbsolutePath().toString());
    }
}