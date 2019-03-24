package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.*;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Класс, позволяющий считывать команды из консоли
 */
public class StreamCliReader implements CliReader {
    private Scanner scanner;

    /**
     * @param input входной поток для чтения
     */
    public StreamCliReader(InputStream input) {
        scanner = new Scanner(input);
    }

    /**
     * @return Возвращает прочитанную со входа строчку
     */
    public String getNextCommand() {
        return scanner.hasNext() ? scanner.nextLine() : null;
    }

    /**
     * Закрывает читающий сканер
     */
    public void closeScanner() {
        scanner.close();
    }
}
