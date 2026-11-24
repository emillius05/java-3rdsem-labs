package org.example.strings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.strings.reader.TextFileReader;
import org.example.strings.service.FileMaskMatcherService;
import org.example.strings.service.TextCleanerService;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_PATH = "data/input.txt";

    public static void main(String[] args) {
        logger.info("=== Запуск лабораторной работы 8 (Вариант 7) ===");
        TextFileReader reader = new TextFileReader();
        TextCleanerService cleanerService = new TextCleanerService();
        FileMaskMatcherService maskService = new FileMaskMatcherService();

        try {
            List<String> lines = reader.readLines(DATA_PATH);
            if (!lines.isEmpty()) {
                String rawText = lines.get(0);
                logger.info("Исходный текст: {}", rawText);
                logger.info("Очистка (String): {}", cleanerService.cleanTextWithString(rawText));
                logger.info("Очистка (Stream): {}", cleanerService.cleanTextWithStream(rawText));
            }
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\\|");
                if (parts.length == 2) {
                    boolean resString = maskService.matchesMaskWithString(parts[0], parts[1]);
                    boolean resStream = maskService.matchesMaskWithStream(parts[0], parts[1]);
                    logger.info("Файл '{}' vs маска '{}': String={}, Stream={}", parts[0], parts[1], resString, resStream);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка чтения файла: {}", e.getMessage());
        }
        logger.info("=== Завершение работы ===");
    }
}
