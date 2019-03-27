package ru.hse.homework.realisation;

import ru.hse.homework.realisation.execution.Executor;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Вспомогательные функции
 */
public class CliUtils {
    /**
     * Получение содержимого файла
     * @param filename название файла
     * @return содержимое файла
     * @throws IOException ошибка при работе с файлом
     */
    public static String getFile(String filename) throws IOException {
        {
            char[] buf = new char[256];
            StringBuilder result = new StringBuilder();
            try (FileReader reader = new FileReader(filename)) {
                int c = reader.read(buf);
                while (c > 0) {
                    if (c < 256) {
                        buf = Arrays.copyOf(buf, c);
                    }
                    result.append(buf);
                    c = reader.read(buf);
                }
            }
            return result.toString();
        }
    }

    /**
     * Заменяет вхождения переменных на значение
     * @param word строка, где заменять
     * @return результат замены
     */
    public static String changeVars(String word) {
        for (Map.Entry<String, String> pair : Executor.getVariables().entrySet()) {
            word = word.replaceAll("\\$" + pair.getKey(), pair.getValue());
        }
        return word;
    }
}
