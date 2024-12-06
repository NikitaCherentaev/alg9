import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/input.txt"; // Входной файл
        StringBuilder output = new StringBuilder(); // StringBuilder для формирования выходного текста
        try {
            String text = new String(Files.readAllBytes(Paths.get(inputFilePath))); // Считываем содержимое файла и создаем из него строку

            int totalChars = text.length(); // Количество символов в тексте
            int charsWithoutSpaces = text.replace(" ", "").length(); // Количество символов без пробелов

            int wordCount = text.isEmpty() ? 0 : text.trim().split(" +").length; // Количество слов

            String[] paragraphs = text.split(" {2,}"); // Разделяем текст на абзацы по двум пробелам
            int paragraphCount = paragraphs.length; // Количество абзацев

            int lineCount = text.split("\n").length; // Количество строк

            String[] pages = text.split("\u000c"); // Разделяем текст по символу форматной страницы
            int pageCount = pages.length; //количество страниц по символу '\u000c'

            output.append("Количество символов в тексте: ").append(totalChars).append("\n")
                    .append("Количество символов без пробелов: ").append(charsWithoutSpaces).append("\n")
                    .append("Количество слов: ").append(wordCount).append("\n")
                    .append("Количество абзацев: ").append(paragraphCount).append("\n")
                    .append("Количество строк: ").append(lineCount).append("\n")
                    .append("Количество страниц: ").append(pageCount).append("\n");

            System.out.println(output); // Вывод результата в консоль
            Files.write(Paths.get("src/outputya.svc"), output.toString().getBytes()); // Запись результата в svc файл
            Files.write(Paths.get("src/output.txt"), output.toString().getBytes()); // Запись результата в txt файл

        } catch (NoSuchFileException e) {
            System.err.println("Ошибка: Файл не найден: " + inputFilePath); // Eсли файл не найден выводим сообщение об ошибке
        } catch (IOException e) { // Обработка других ошибок ввода вывода
            e.printStackTrace(); // Вывод стектрейса
        }
    }
}