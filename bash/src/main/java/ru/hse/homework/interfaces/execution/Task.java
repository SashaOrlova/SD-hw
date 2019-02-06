package ru.hse.homework.interfaces.execution;

import java.io.IOException;

public interface Task {
    void setArgs(String[] args) throws Exception;
    String execute() throws Exception;
    String[] getArgs();
}
