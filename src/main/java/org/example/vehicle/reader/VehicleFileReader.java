package org.example.vehicle.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleFileReader {
    private static final Logger logger = LogManager.getLogger(VehicleFileReader.class);

    public List<String> readLines(String filePath) throws IOException {
        logger.info("Чтение файла данных: {}", filePath);
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            logger.error("Файл не найден: {}", filePath);
            throw new IOException("Файл не найден: " + filePath);
        }
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                .collect(Collectors.toList());
        logger.info("Успешно прочитано {} строк из файла", lines.size());
        return lines;
    }
}
