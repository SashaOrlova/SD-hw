package ru.hse.homework.interfaces.analizers;

import ru.hse.homework.interfaces.execution.Task;

/**
 * Make tasks from tokens
 */
public interface Parser {
    /**
     *
     * @param words tokens for analise
     * @return tasks for execution
     * @throws Exception
     */
    Task[] getTasks(String[] words) throws Exception;
}
