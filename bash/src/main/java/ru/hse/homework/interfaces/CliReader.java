package ru.hse.homework.interfaces;

/**
 * Read lines from input
 */
public interface CliReader {
    /**
     * get command from input
     * @return new command
     */
    String getNextCommand();
}
