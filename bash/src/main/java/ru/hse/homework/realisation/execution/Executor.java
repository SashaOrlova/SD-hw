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
import java.util.HashMap;

public class Executor {
    private static HashMap<String, String> variables = new HashMap<>();

    public static void addToContext(String symb, String value) {
        variables.put(symb, value);
    }

    public static String getFromContext(String symb) {
        return variables.get(symb);
    }

    public static void main(String[] args) {
        CliReader cliReader = new StreamCliReader(System.in);
        Lexer lexer = new SpaceLexer();
        Parser parser = new WordsParser();
        CliWriter cliWriter = new StreamCliWriter(System.out);
        while (true) {
            String command = cliReader.getNextCommand();
            String[] taskResult = new String[1];
            try {
                String[] lexems = lexer.getTokens(command);
                Task[] tasks = parser.getTasks(lexems);
                for (Task task : tasks) {
                    if (taskResult[0] != null) {
                        task.setArgs(taskResult);
                    }
                    taskResult[0] = task.execute();
                }
                cliWriter.writeCommandResult(taskResult[0].getBytes());
            } catch (Exit.ExitException e) {
                ((StreamCliReader) cliReader).closeScanner();
                System.exit(0);
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
}
