package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.*;

import java.io.InputStream;
import java.util.Scanner;

public class StreamReader implements Reader {
    private Scanner scanner = null;
    public StreamReader(InputStream input) {
        scanner = new Scanner(input);
    }

    public String getNextCommand() {
        return scanner.hasNext() ? scanner.nextLine() : null;
    }
}
