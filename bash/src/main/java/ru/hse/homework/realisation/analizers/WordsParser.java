package ru.hse.homework.realisation.analizers;

import org.apache.commons.lang3.ArrayUtils;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.realisation.CliUtils;
import ru.hse.homework.realisation.execution.Executor;
import ru.hse.homework.realisation.execution.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Парсит набор токенов, превращая его в исполняемые задачки
 */
public class WordsParser implements Parser {

    /**
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
            default:
                if (taskWithArgs.length > 2 && "=".equals(taskWithArgs[1])) {
                    task = new Assignment();
                    task.setArgs(new String[]{command, taskWithArgs[2]});
                    return task;
                } else {
                    task = new ExternalTask();
                    task.setArgs(ArrayUtils.addAll(
                            new String[]{command},
                            Arrays.copyOfRange(taskWithArgs, Math.min(1, taskWithArgs.length), taskWithArgs.length)
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
            word = CliUtils.changeVars(word);
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
}
