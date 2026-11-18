package org.example.europe.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.europe.entity.Europe;

import java.util.List;

public class TerritorialChangeParser {
    private static final Logger logger = LogManager.getLogger(TerritorialChangeParser.class);
    private static final String DELIMITER = ";";

    public void parseAndPopulate(Europe europe, List<String> lines) {
        logger.info("Начало парсинга {} строк", lines.size());
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length < 5) {
                logger.warn("Пропуск некорректной строки (недостаточно полей): {}", line);
                continue;
            }
            try {
                int year = Integer.parseInt(parts[0].trim());
                String event = parts[1].trim();
                String state = parts[2].trim();
                double area = Double.parseDouble(parts[3].trim());
                String status = parts[4].trim();

                // Создание объекта внутреннего класса через внешний объект Europe
                Europe.TerritorialChange change = europe.new TerritorialChange(year, event, state, area, status);
                europe.addChange(change);
                logger.debug("Успешно добавлена запись: {}", change);
            } catch (IllegalArgumentException e) {
                logger.warn("Игнорирование строки с невалидными данными [{}]: {}", line, e.getMessage());
            }
        }
        logger.info("Парсинг завершен. Всего валидных записей в Европе: {}", europe.getHistory().size());
    }
}
