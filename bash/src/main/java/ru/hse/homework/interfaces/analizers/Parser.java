package ru.hse.homework.interfaces.analizers;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.analizers.WordsParser;

public interface Parser {
    public Task[] getTasks(String[] words) throws Exception;
}
