package ru.hse.homework.interfaces;

import java.io.IOException;

/**
 * Write result in output
 */
public interface CliWriter {
    /**
     * write command result in output
     * @param result task result
     * @throws IOException exception during writing
     */
    void writeCommandResult(byte[] result) throws IOException;
}
