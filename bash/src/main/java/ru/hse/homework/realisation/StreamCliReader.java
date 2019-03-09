package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.*;

import java.io.InputStream;
import java.util.Scanner;

public class StreamCliReader implements CliReader {
    private Scanner scanner;
    public StreamCliReader(InputStream input) {
        scanner = new Scanner(input);
    }

    public String getNextCommand() {
        return scanner.hasNext() ? scanner.nextLine() : null;
    }

    public void closeScanner() {
        scanner.close();
    }
}
