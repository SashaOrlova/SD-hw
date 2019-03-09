package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.CliWriter;

import java.io.IOException;
import java.io.OutputStream;

public class StreamCliWriter implements CliWriter {
    private OutputStream outputStream;
    public StreamCliWriter(OutputStream output) {
        outputStream = output;
    }

    public void writeCommandResult(byte[] result) throws IOException {
        outputStream.write(result);
    }
}
