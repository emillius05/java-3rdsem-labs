package org.example.text.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileManager {
    private static final Logger logger = LogManager.getLogger(TextFileManager.class);

    public String readText(String inputPath) throws IOException {
        logger.info("Чтение текста из файла: {}", inputPath);
        Path path = Path.of(inputPath);
        if (!Files.exists(path)) {
            logger.error("Входной файл не найден: {}", inputPath);
            throw new IOException("Файл не найден: " + inputPath);
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public void writeText(String outputPath, String content) throws IOException {
        logger.info("Запись результата в файл: {}", outputPath);
        Path path = Path.of(outputPath);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }
        Files.writeString(path, content, StandardCharsets.UTF_8);
        logger.info("Файл успешно сохранён: {}", outputPath);
    }
}
