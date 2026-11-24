package org.example.strings.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileMaskMatcherService {
    private static final Logger logger = LogManager.getLogger(FileMaskMatcherService.class);

    /**
     * Вариант C, задача 7 (способ 1: методы класса String + регулярные выражения).
     * Проверяет, удовлетворяет ли имя файла маске ('?' - 1 символ, '*' - 0 и более символов).
     */
    public boolean matchesMaskWithString(String fileName, String mask) {
        logger.info("Проверка имени файла '{}' по маске '{}' (методы String)", fileName, mask);
        validateInputs(fileName, mask);
        String regex = buildRegexWithString(mask);
        boolean matched = fileName.matches(regex);
        logger.debug("Regex: '{}', результат: {}", regex, matched);
        return matched;
    }

    /**
     * Вариант C, задача 7 (способ 2: Stream API + регулярные выражения).
     */
    public boolean matchesMaskWithStream(String fileName, String mask) {
        logger.info("Проверка имени файла '{}' по маске '{}' (Stream API)", fileName, mask);
        validateInputs(fileName, mask);
        String regex = buildRegexWithStream(mask);
        boolean matched = Pattern.compile(regex, Pattern.DOTALL).matcher(fileName).matches();
        logger.debug("Regex (Stream): '{}', результат: {}", regex, matched);
        return matched;
    }

    private String buildRegexWithString(String mask) {
        String escaped = mask.replaceAll("([\\.\\^\\$\\+\\(\\)\\[\\]\\{\\}\\\\|])", "\\\\$1");
        return "^" + escaped.replace("*", ".*").replace("?", ".") + "$";
    }

    private String buildRegexWithStream(String mask) {
        String body = mask.chars()
                .mapToObj(c -> (char) c)
                .map(ch -> switch (ch) {
                    case '*' -> ".*";
                    case '?' -> ".";
                    default -> Pattern.quote(String.valueOf(ch));
                })
                .collect(Collectors.joining());
        return "^" + body + "$";
    }

    private void validateInputs(String fileName, String mask) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым или null");
        }
        if (mask == null || mask.isBlank()) {
            throw new IllegalArgumentException("Маска не может быть пустой или null");
        }
    }
}
