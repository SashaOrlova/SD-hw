package ru.hse.homework.interfaces.execution;

/**
 * Shell command
 */
public interface Task {
    /** set task arguments
     * @param args arguments for command
     * @throws Exception if args format is wrong
     */
    void setArgs(String[] args) throws Exception;

    /** Execute task
     * @return result of task
     * @throws Exception if smth wrong during task execution
     */
    String execute(String[] input) throws Exception;
    String[] getArgs();
}
