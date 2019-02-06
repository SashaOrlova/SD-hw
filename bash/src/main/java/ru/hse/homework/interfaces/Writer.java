package ru.hse.homework.interfaces;

import java.io.IOException;

public interface Writer {
    void writeCommandResult(byte[] result) throws IOException;
}
