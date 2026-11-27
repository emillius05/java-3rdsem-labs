package org.example.locale.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.locale.exception.InvalidLocaleNumberException;
import org.example.locale.model.LocaleNumberEntry;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class LocaleNumberParser {
    private static final Logger logger = LogManager.getLogger(LocaleNumberParser.class);
    private static final String DELIMITER = ";";
    private static final BigDecimal MAX_ALLOWED = BigDecimal.valueOf(Double.MAX_VALUE);
    private static final BigDecimal MIN_ALLOWED = BigDecimal.valueOf(Double.MAX_VALUE).negate();

    public Locale parseLocaleTag(String tag) throws InvalidLocaleNumberException {
        if (tag == null || tag.isBlank()) {
            throw new InvalidLocaleNumberException("Отсутствует локаль");
        }
        Locale locale = Locale.forLanguageTag(tag.trim().replace('_', '-'));
        if (locale.getLanguage().isEmpty() || !List.of(Locale.getISOLanguages()).contains(locale.getLanguage())) {
            throw new InvalidLocaleNumberException("Некорректная локаль: " + tag);
        }
        return locale;
    }

    public BigDecimal parseNumberWithLocale(String rawNumber, Locale locale) throws InvalidLocaleNumberException {
        if (rawNumber == null || rawNumber.isBlank()) {
            throw new InvalidLocaleNumberException("Отсутствует числовое значение");
        }
        String normalized = rawNumber.trim().replace("\u00A0", "").replace(" ", "");
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        if (nf instanceof DecimalFormat df) {
            df.setParseBigDecimal(true);
            DecimalFormatSymbols syms = df.getDecimalFormatSymbols();
            char decSep = syms.getDecimalSeparator();
            char grpSep = syms.getGroupingSeparator();
            if (decSep == ',' && normalized.indexOf('.') >= 0 && normalized.indexOf(',') < 0) {
                normalized = normalized.replace('.', ',');
            } else if (decSep == '.' && normalized.indexOf(',') >= 0 && normalized.indexOf('.') < 0) {
                normalized = normalized.replace(',', '.');
            }
            String grpQuoted = Pattern.quote(String.valueOf(grpSep));
            String decQuoted = Pattern.quote(String.valueOf(decSep));
            String allowedPattern = "^[+-]?(?:\\d|" + grpQuoted + ")+(?:" + decQuoted + "\\d+)?(?:[eE][+-]?\\d+)?$";
            if (!normalized.matches(allowedPattern)) {
                throw new InvalidLocaleNumberException("Некорректный формат числа '" + rawNumber + "' для локали " + locale);
            }
        }

        ParsePosition pos = new ParsePosition(0);
        Number parsed = nf.parse(normalized, pos);
        if (parsed == null || pos.getIndex() != normalized.length()) {
            throw new InvalidLocaleNumberException("Строка не является корректным числом: '" + rawNumber + "' (" + locale + ")");
        }

        BigDecimal decimal = (parsed instanceof BigDecimal bd) ? bd : new BigDecimal(parsed.toString());
        if (decimal.compareTo(MAX_ALLOWED) > 0 || decimal.compareTo(MIN_ALLOWED) < 0) {
            throw new InvalidLocaleNumberException("Число выходит за допустимые пределы: " + decimal);
        }
        return decimal;
    }

    public List<LocaleNumberEntry> parseLines(List<String> lines) throws InvalidLocaleNumberException {
        if (lines == null || lines.isEmpty()) {
            throw new InvalidLocaleNumberException("Файл не содержит записей с числами");
        }
        List<LocaleNumberEntry> result = new ArrayList<>();
        try {
            for (String line : lines) {
                String[] parts = line.split(DELIMITER, -1);
                if (parts.length < 2) {
                    throw new InvalidLocaleNumberException("Некорректная запись (ожидается 'локаль;число'): " + line);
                }
                Locale locale = parseLocaleTag(parts[0]);
                BigDecimal value = parseNumberWithLocale(parts[1], locale);
                result.add(new LocaleNumberEntry(locale, parts[1].trim(), value));
                logger.debug("Распознано число {} ({}) -> {}", parts[1].trim(), locale, value);
            }
        } catch (OutOfMemoryError e) {
            logger.fatal("Нехватка памяти при разборе чисел");
            throw new InvalidLocaleNumberException("Нехватка памяти при обработке записей", e);
        }
        if (result.isEmpty()) {
            throw new InvalidLocaleNumberException("Не найдено ни одной корректной записи");
        }
        return result;
    }
}
