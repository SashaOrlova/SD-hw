package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.interfaces.execution.Task;

import java.io.*;

import static org.junit.Assert.*;

public class CatTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws Exception {
        File createdFile = folder.newFile("test.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(createdFile));
        writer.write("Hello world!");
        writer.close();
        Task catTask = new Cat();
        catTask.setArgs(new String[]{createdFile.getAbsolutePath()});
        String res = catTask.execute();
        assertEquals("Hello world!", res);
    }
}