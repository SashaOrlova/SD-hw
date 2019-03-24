package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.CliWriter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Класс, позволяющий писать команды в консоль
 */
public class StreamCliWriter implements CliWriter {
    private OutputStream outputStream;

    /**
     * @param output поток куда будет происходить запись
     */
    public StreamCliWriter(OutputStream output) {
        outputStream = output;
    }

    /**
     * @param result строка для вывода
     * @throws IOException при ошибки записи в поток
     */
    public void writeCommandResult(byte[] result) throws IOException {
        outputStream.write(result);
    }
}
