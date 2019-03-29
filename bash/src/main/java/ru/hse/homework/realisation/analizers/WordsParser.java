package ru.hse.homework.realisation.analizers;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.realisation.CliUtils;
import ru.hse.homework.realisation.execution.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Парсит набор токенов, превращая его в исполняемые задачки
 */
public class WordsParser implements Parser {

    /**
     * Получение одной задачи
     * @param tokens набор токенов, полученный из лексера
     * @return первую найденную задачу для выполнения
     * @throws Exception если при составлении задачи обнаружена ошибка
     */
    public Task getOneTask(String[] tokens) throws Exception {
        String[] taskWithArgs = getWhileNotEnd(tokens);
        Task task;
        String command = taskWithArgs[0];
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
                break;
            default:
                if (taskWithArgs.length > 2 && "=".equals(taskWithArgs[1])) {
                    task = new Assignment();
                    task.setArgs(new String[]{command, taskWithArgs[2]});
                    return task;
                } else {
                    task = new ExternalTask();

                    task.setArgs(concatArray(
                            Arrays.copyOfRange(taskWithArgs, Math.min(1, taskWithArgs.length), taskWithArgs.length),
                            command
                        ));
                    return task;
                }
        }
        task.setArgs(Arrays.copyOfRange(taskWithArgs, Math.min(1, taskWithArgs.length), taskWithArgs.length));
        return task;
    }

    private String[] getWhileNotEnd(String[] words) {
        ArrayList<String> args = new ArrayList<>();
        for (String word: words) {
            if (!word.startsWith("'")) {
                word = CliUtils.changeVars(word);
            }
            if (word.startsWith("\"") || word.startsWith("'")) {
                word = word.substring(1, word.length() - 1);
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

    private String[] concatArray(String[] array, String string) {
        String[] newArray = new String[array.length + 1];
        newArray[0] = string;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return newArray;
    }
}
