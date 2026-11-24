package org.example.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.text.composite.TextComponent;
import org.example.text.io.TextFileManager;
import org.example.text.parser.AbstractTextParser;
import org.example.text.parser.TextParserChainBuilder;
import org.example.text.service.TextProcessingService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String INPUT_PATH = "data/input.txt";
    private static final String OUTPUT_PATH = "data/output.txt";

    public static void main(String[] args) {
        logger.info("=== Запуск лабораторной работы 9 (Вариант 7) ===");
        TextFileManager fileManager = new TextFileManager();
        TextProcessingService service = new TextProcessingService();
        AbstractTextParser parserChain = TextParserChainBuilder.buildChain();

        try {
            String rawText = fileManager.readText(INPUT_PATH);
            TextComponent parsedText = parserChain.parse(rawText);

            String normalizedText = service.normalizeWhitespace(parsedText);
            List<String> sortedWords = service.sortWordsByVowelRatio(parsedText);

            String formattedWords = sortedWords.stream()
                    .map(w -> String.format(Locale.US, "%s (%.2f)", w, service.calculateVowelRatio(w)))
                    .collect(Collectors.joining(System.lineSeparator()));

            String outputContent = "=== Нормализованный текст ===" + System.lineSeparator()
                    + normalizedText + System.lineSeparator() + System.lineSeparator()
                    + "=== Слова по возрастанию доли гласных ===" + System.lineSeparator()
                    + formattedWords;

            fileManager.writeText(OUTPUT_PATH, outputContent);
            logger.info("Обработка завершена успешно");
        } catch (IOException e) {
            logger.error("Ошибка работы с файлами: {}", e.getMessage());
        }
    }
}
