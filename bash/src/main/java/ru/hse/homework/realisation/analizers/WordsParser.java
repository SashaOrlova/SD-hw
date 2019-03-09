package ru.hse.homework.realisation.analizers;

import org.apache.commons.lang3.ArrayUtils;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.realisation.execution.Executor;
import ru.hse.homework.realisation.execution.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class WordsParser implements Parser {
    public Task[] getTasks(String[] words) throws Exception {
        if (words.length == 0)
            throw new ParserException("Wrong words number in parser");
        ArrayList<Task> tasks = new ArrayList<>();
        while (words.length > 0) {
            String command = words[0];
            words = Arrays.copyOfRange(words, 1, words.length);
            String[] args = getWhileNotEnd(words);
            Task task;
            switch (command) {
                case Wc.COMMAND:
                    task = new Wc();
                    break;
                case Cat.COMMAND:
                    task = new Cat();
                    break;
                case Echo.COMMAND:
                    task = new Echo();
                    break;
                case Pwd.COMMAND:
                    task = new Pwd();
                    break;
                case Exit.COMMAND:
                    task = new Exit();
                    break;
                default:
                    if (words.length > 0 && "=".equals(words[0])) {
                        task = new Assignment();
                        task.setArgs(new String[]{command, words[1]});
                        tasks.add(task);
                        words = Arrays.copyOfRange(words, Math.min(2, words.length), words.length);
                        continue;
                    } else {
                        task = new ExternalTask();
                        task.setArgs(ArrayUtils.addAll(new String[]{command}, args));
                        tasks.add(task);
                        words = Arrays.copyOfRange(words, Math.min(args.length, words.length), words.length);
                        continue;
                    }
            }
            task.setArgs(args);
            tasks.add(task);
            words = Arrays.copyOfRange(words, Math.min(args.length + 1, words.length), words.length);
        }
        Task[] tasksArray = new Task[tasks.size()];
        return tasks.toArray(tasksArray);
    }

    private String[] getWhileNotEnd(String[] words) {
        ArrayList<String> args = new ArrayList<>();
        for (String word: words) {
            if (word.startsWith("$")) {
                word = Executor.getFromContext(word.substring(1));
            }
            if ("|".equals(word)) {
                String[] arrayArgs = new String[args.size()];
                return args.toArray(arrayArgs);
            } else {
                args.add(word);
            }
        }
        String[] arrayArgs = new String[args.size()];
        return args.toArray(arrayArgs);
    }

    private static class ParserException extends Exception {
        ParserException(String message) {
            super(message);
        }
    }
}
