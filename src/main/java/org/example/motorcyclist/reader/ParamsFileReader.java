package org.example.motorcyclist.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.motorcyclist.entity.AppParams;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * читает параметры запуска из файла, если с данными беда, ставит значения по умолчанию.
 */
public class ParamsFileReader {
    private static final Logger logger = LogManager.getLogger(ParamsFileReader.class);

    private static final String NAME_KEY = "name";
    private static final String MIN_PRICE_KEY = "minPrice";
    private static final String MAX_PRICE_KEY = "maxPrice";

    private static final String DEFAULT_NAME = "Иван";
    private static final double DEFAULT_MIN_PRICE = 100;
    private static final double DEFAULT_MAX_PRICE = 300;

    public AppParams read(String filePath) {
        Properties properties = new Properties();

        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            properties.load(reader);
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Не удалось прочитать файл параметров {}: {}. "
                    + "Берем значения по умолчанию", filePath, e.getMessage());
            return new AppParams(DEFAULT_NAME, DEFAULT_MIN_PRICE, DEFAULT_MAX_PRICE);
        }

        logger.info("Чтение файла параметров {}", filePath);

        String name = readName(properties.getProperty(NAME_KEY));
        double minPrice = readPrice(properties.getProperty(MIN_PRICE_KEY),
                MIN_PRICE_KEY, DEFAULT_MIN_PRICE);
        double maxPrice = readPrice(properties.getProperty(MAX_PRICE_KEY),
                MAX_PRICE_KEY, DEFAULT_MAX_PRICE);

        if (minPrice > maxPrice) {
            logger.warn("Минимальная цена {} больше максимальной {}, "
                    + "берем значения по умолчанию", minPrice, maxPrice);
            minPrice = DEFAULT_MIN_PRICE;
            maxPrice = DEFAULT_MAX_PRICE;
        }

        logger.info("Параметры: мотоциклист {}, цена от {} до {}", name, minPrice, maxPrice);
        return new AppParams(name, minPrice, maxPrice);
    }

    /**
     * пустое или пропущенное имя заменяем на имя по умолчанию.
     */
    private String readName(String value) {
        if (value == null || value.isBlank()) {
            logger.warn("Имя мотоциклиста не задано, берем по умолчанию: {}", DEFAULT_NAME);
            return DEFAULT_NAME;
        }

        return value.trim();
    }

    /**
     * цена должна быть конечным неотрицательным числом, иначе берем значение по умолчанию.
     */
    private double readPrice(String value, String key, double defaultValue) {
        if (value == null) {
            logger.warn("Параметр {} не задан, берем по умолчанию: {}", key, defaultValue);
            return defaultValue;
        }

        try {
            double price = Double.parseDouble(value.trim());
            if (!Double.isFinite(price) || price < 0) {
                logger.warn("Параметр {} некорректный ({}), берем по умолчанию: {}",
                        key, value, defaultValue);
                return defaultValue;
            }
            return price;
        } catch (NumberFormatException e) {
            logger.warn("Параметр {} не число ({}), берем по умолчанию: {}",
                    key, value, defaultValue);
            return defaultValue;
        }
    }
}