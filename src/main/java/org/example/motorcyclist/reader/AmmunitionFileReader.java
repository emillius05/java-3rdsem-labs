package org.example.motorcyclist.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.parser.AmmunitionParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * читает файл с амуницией, кривые строки пропускает и пишет в лог.
 */
public class AmmunitionFileReader {
    private static final Logger logger = LogManager.getLogger(AmmunitionFileReader.class);
    private static final String COMMENT_PREFIX = "#";

    private final AmmunitionParser parser = new AmmunitionParser();

    public List<Ammunition> read(String filePath) {
        List<Ammunition> result = new ArrayList<>();
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException | InvalidPathException e) {
            logger.error("Не удалось прочитать файл {}: {}", filePath, e.getMessage());
            return result;
        }

        logger.info("Чтение файла {}, строк: {}", filePath, lines.size());

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank() || line.strip().startsWith(COMMENT_PREFIX)) {
                continue;
            }

            try {
                Ammunition ammunition = parser.parse(line);
                result.add(ammunition);
                logger.debug("Строка {} обработана: {}", i + 1, ammunition);
            } catch (IllegalArgumentException e) {
                logger.warn("Строка {} проигнорирована ({}): {}", i + 1, e.getMessage(), line);
            }
        }

        logger.info("Успешно создано объектов: {}", result.size());
        return result;
    }
}