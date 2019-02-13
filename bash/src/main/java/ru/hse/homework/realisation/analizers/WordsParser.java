package ru.hse.homework.realisation.analizers;

import org.apache.commons.cli.*;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.realisation.execution.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class WordsParser implements Parser {
    Options options;
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd;

    public WordsParser() {
        options = new Options();
        Option i = new Option("i", "i", false, "");
        i.setRequired(false);
        options.addOption(i);
        Option w = new Option("w", "w", false, "");
        w.setRequired(false);
        options.addOption(w);
        Option A = new Option("A", "A", true, "");
        i.setRequired(false);
        options.addOption(A);
    }

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
                case Grep.COMMAND:
                    task = new Grep();
                    ArrayList<String> grep_args = new ArrayList<>();
                    cmd = parser.parse(options, args);
                    if (cmd.hasOption("i")) {
                        grep_args.add("i");
                    }
                    if (cmd.hasOption("A")) {
                        grep_args.add("A");
                        grep_args.add(cmd.getOptionValue("A"));
                    }
                    if (cmd.hasOption("w")) {
                        grep_args.add("w");
                    }
                    grep_args.add(args[args.length - 2]);
                    grep_args.add(args[args.length - 1]);
                    task.setArgs(grep_args.toArray(new String[0]));
                default:
                    throw new Exception("Undefine command");
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
