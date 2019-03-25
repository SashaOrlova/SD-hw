package ru.hse.homework.realisation.execution;

import ru.hse.homework.interfaces.CliReader;
import ru.hse.homework.interfaces.CliWriter;
import ru.hse.homework.interfaces.analizers.Lexer;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.StreamCliReader;
import ru.hse.homework.realisation.StreamCliWriter;
import ru.hse.homework.realisation.analizers.SpaceLexer;
import ru.hse.homework.realisation.analizers.WordsParser;
import ru.hse.homework.realisation.execution.tasks.Exit;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, выполняющий задачи
 */
public class Executor {
    private static CliWriter cliWriter = new StreamCliWriter(System.out);

    private static HashMap<String, String> variables = new HashMap<>();

    /**
     * Помещает значение переменной в scope
     * @param symb название переменной
     * @param value значение переменной
     */
    public static void addToContext(String symb, String value) {
        variables.put(symb, value);
    }

    /**
     * @return имеющийся контекст, переменные отсортированы в порядке возрастания длинны
     */
    public static HashMap<String, String> getVariables() {
        return variables.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(String::length).reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
    /** Главная функция
     * @param args
     */
    public static void main(String[] args) {
        StreamCliReader cliReader = new StreamCliReader(System.in);
        Lexer lexer = new SpaceLexer();
        Parser parser = new WordsParser();
        while (true) {
            String command = cliReader.getNextCommand();
            try {
                String[] lexems = lexer.getTokens(command);
                String[] result = null;
                while (lexems.length > 0) {
                    Task task = parser.getOneTask(lexems);
                    result = new String[]{task.execute(result)};
                    lexems = Arrays.copyOfRange(
                            lexems, Math.min(task.getArgs().length + 2, lexems.length), lexems.length
                    );
                }
                cliWriter.writeCommandResult(
                        ((result == null || result[0] == null ? "" : result[0]) + System.lineSeparator()).getBytes());
            } catch (Exit.ExitException e) {
                cliReader.closeScanner();
                return;
            }
            catch (Exception e) {
                try {
                    cliWriter.writeCommandResult(e.getMessage().getBytes());
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }

    public static void printToErrorStream(String output) {
        try {
            cliWriter.writeCommandResult((output).getBytes());
        } catch (IOException e) {
            System.out.println(output);
        }
    }
}
