package org.example.strings.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextCleanerService {
    private static final Logger logger = LogManager.getLogger(TextCleanerService.class);
    private static final Pattern NON_LETTER_PATTERN = Pattern.compile("[^\\p{L}\\s]+");
    private static final Pattern MULTI_SPACE_PATTERN = Pattern.compile("\\s+");

    /**
     * Вариант А, задача 7 (способ 1: методы класса String + регулярные выражения).
     * Удаляет из текста все символы, кроме пробелов, не являющиеся буквами.
     * Между последовательностями подряд идущих букв оставляет один пробел.
     */
    public String cleanTextWithString(String text) {
        logger.info("Очистка текста (методы String)");
        if (text == null) {
            throw new IllegalArgumentException("Входной текст не может быть null");
        }
        String replaced = text.replaceAll("[^\\p{L}\\s]+", " ");
        String result = replaced.replaceAll("\\s+", " ").trim();
        logger.debug("Результат (String): '{}'", result);
        return result;
    }

    /**
     * Вариант А, задача 7 (способ 2: Stream API + регулярные выражения).
     */
    public String cleanTextWithStream(String text) {
        logger.info("Очистка текста (Stream API)");
        if (text == null) {
            throw new IllegalArgumentException("Входной текст не может быть null");
        }
        String result = Arrays.stream(NON_LETTER_PATTERN.split(text))
                .map(part -> MULTI_SPACE_PATTERN.matcher(part).replaceAll(" ").trim())
                .filter(part -> !part.isEmpty())
                .collect(Collectors.joining(" "));
        logger.debug("Результат (Stream): '{}'", result);
        return result;
    }
}
