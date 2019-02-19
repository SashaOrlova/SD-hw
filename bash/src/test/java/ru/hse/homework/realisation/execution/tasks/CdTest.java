package ru.hse.homework.realisation.execution.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.io.*;

import static org.junit.Assert.*;

public class CdTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws Exception {
        Environment environment = new Environment();
        System.out.println(environment.getCurrentPath().normalize().toString());
    }
}