package ru.hse.homework.realisation;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Environment {
    private Path currentPath = null;

    public Environment() {
        this.currentPath = Paths.get(".").toAbsolutePath();
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Path currentPath) {
        this.currentPath = currentPath;
    }
}
