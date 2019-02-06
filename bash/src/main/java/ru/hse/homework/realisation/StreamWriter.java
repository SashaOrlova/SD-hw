package ru.hse.homework.realisation;

import ru.hse.homework.interfaces.Writer;

import java.io.IOException;
import java.io.OutputStream;

public class StreamWriter implements Writer {
    private OutputStream outputStream;
    public StreamWriter(OutputStream output) {
        outputStream = output;
    }

    public void writeCommandResult(byte[] result) throws IOException {
        outputStream.write(result);
    }
}
