package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.interfaces.execution.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.junit.Assert.*;

public class WcTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void simpleTest() throws Exception {
        File createdFile = folder.newFile("test.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(createdFile));
        writer.write("test test test\n line line line");
        writer.close();
        Task wcTask = new Wc();
        wcTask.setArgs(new java.lang.String[]{createdFile.getAbsolutePath()});
        String res = wcTask.execute(null);
        assertEquals("2 6 30", res);
    }
}