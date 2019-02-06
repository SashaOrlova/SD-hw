package ru.hse.homework.realisation.execution;

import ru.hse.homework.interfaces.Reader;
import ru.hse.homework.interfaces.Writer;
import ru.hse.homework.interfaces.analizers.Lexer;
import ru.hse.homework.interfaces.analizers.Parser;
import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.StreamReader;
import ru.hse.homework.realisation.StreamWriter;
import ru.hse.homework.realisation.analizers.SpaceLexer;
import ru.hse.homework.realisation.analizers.WordsParser;

import java.io.IOException;

public class Executor {
    public static void main(String[] args) {
        Reader reader = new StreamReader(System.in);
        Lexer lexer = new SpaceLexer();
        Parser parser = new WordsParser();
        Writer writer = new StreamWriter(System.out);
        while (true) {
            String command = reader.getNextCommand();
            String[] lexems = lexer.getToken(command);
            String[] taskResult = new String[1];
            try {
                Task[] tasks = parser.getTasks(lexems);
                for (Task task : tasks) {
                    if (taskResult[0] != null) {
                        task.setArgs(taskResult);
                    }
                    taskResult[0] = task.execute();
                }
                writer.writeCommandResult(taskResult[0].getBytes());
            } catch (Exception e) {
                try {
                    writer.writeCommandResult(e.getMessage().getBytes());
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
