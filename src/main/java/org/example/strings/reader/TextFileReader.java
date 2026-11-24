package org.example.strings.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileReader {
    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public List<String> readLines(String filePath) throws IOException {
        logger.info("Чтение входного файла: {}", filePath);
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            logger.error("Файл не найден: {}", filePath);
            throw new IOException("Файл не найден: " + filePath);
        }
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .filter(line -> !line.isBlank())
                .toList();
        logger.info("Прочитано строк: {}", lines.size());
        return lines;
    }
}
