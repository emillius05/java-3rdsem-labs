package org.example.locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.locale.exception.InvalidLocaleNumberException;
import org.example.locale.model.LocaleNumberEntry;
import org.example.locale.parser.LocaleNumberParser;
import org.example.locale.reader.NumberFileReader;
import org.example.locale.service.NumberStatisticsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/numbers.txt";

    public static void main(String[] args) {
        String path = args.length > 0 ? args[0] : DEFAULT_FILE;
        logger.info("=== Старт ЛР10: обработка чисел с плавающей запятой по локалям ===");

        NumberFileReader reader = new NumberFileReader();
        LocaleNumberParser parser = new LocaleNumberParser();
        NumberStatisticsService statsService = new NumberStatisticsService();

        try {
            List<String> lines = reader.readLines(path);
            List<LocaleNumberEntry> validEntries = new ArrayList<>();

            for (String line : lines) {
                try {
                    validEntries.addAll(parser.parseLines(List.of(line)));
                } catch (InvalidLocaleNumberException e) {
                    logger.warn("Пропуск некорректной записи [{}]: {}", line, e.getMessage());
                }
            }

            if (validEntries.isEmpty()) {
                throw new InvalidLocaleNumberException("В файле нет ни одного корректного числа");
            }

            BigDecimal sum = statsService.calculateSum(validEntries);
            BigDecimal avg = statsService.calculateAverage(validEntries);

            logger.info("Корректных чисел: {}", validEntries.size());
            logger.info("Сумма: {}", sum.toPlainString());
            logger.info("Среднее: {}", avg.toPlainString());
        } catch (InvalidLocaleNumberException e) {
            logger.error("Ошибка выполнения ЛР10: {}", e.getMessage());
        } catch (Throwable t) {
            logger.fatal("Критическая ошибка: {}", t.getMessage());
        }

        logger.info("=== Завершение работы программы ===");
    }
}
