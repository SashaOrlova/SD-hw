package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.realisation.Environment;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LsTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void lsInCurrentDir() throws Exception {
        Environment environment = new Environment();
        environment.setCurrentPath(folder.getRoot().toPath());
        folder.newFile("foo.cpp");
        folder.newFile(".file");
        folder.newFolder("innerFolder");

        String[] expected = {"foo.cpp", "innerFolder", ".file"};

        Ls lsTask = new Ls();
        String result = lsTask.execute(environment);

        String[] actual = result.split("\n");

        Arrays.sort(expected);
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void lsSomeDir() throws Exception {
        Environment environment = new Environment();
        environment.setCurrentPath(folder.getRoot().toPath());
        folder.newFile("foo.cpp");
        folder.newFile(".file");

        File innerFolder = folder.newFolder("innerFolder");
        folder.newFile(innerFolder.getName() + File.separator + "file1");
        folder.newFolder(innerFolder.getName(), "folder1");

        String[] expected = {"file1", "folder1"};

        Ls lsTask = new Ls();
        lsTask.setArgs(new String[]{"innerFolder"});
        String result = lsTask.execute(environment);

        String[] actual = result.split("\n");

        Arrays.sort(expected);
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}