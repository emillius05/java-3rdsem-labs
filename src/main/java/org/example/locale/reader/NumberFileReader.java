package org.example.locale.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.locale.exception.InvalidLocaleNumberException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class NumberFileReader {
    private static final Logger logger = LogManager.getLogger(NumberFileReader.class);

    public List<String> readLines(String filePath) throws InvalidLocaleNumberException {
        if (filePath == null || filePath.isBlank()) {
            throw new InvalidLocaleNumberException("Не задан путь к файлу");
        }
        Path path = Path.of(filePath);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            logger.error("Файл не найден: {}", filePath);
            throw new InvalidLocaleNumberException("Файл не найден по пути: " + filePath);
        }
        try {
            logger.info("Чтение символьного файла: {}", path.toAbsolutePath());
            return Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Ошибка чтения файла {}: {}", filePath, e.getMessage());
            throw new InvalidLocaleNumberException("Ошибка чтения файла: " + filePath, e);
        } catch (OutOfMemoryError e) {
            logger.fatal("Недостаточно памяти при чтении файла {}", filePath);
            throw new InvalidLocaleNumberException("Нехватка памяти при чтении файла", e);
        }
    }
}
