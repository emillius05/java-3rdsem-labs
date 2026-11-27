package org.example.locale;

import org.example.locale.exception.InvalidLocaleNumberException;
import org.example.locale.model.LocaleNumberEntry;
import org.example.locale.parser.LocaleNumberParser;
import org.example.locale.reader.NumberFileReader;
import org.example.locale.service.NumberStatisticsService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocaleNumbersTest {

    private final LocaleNumberParser parser = new LocaleNumberParser();
    private final NumberStatisticsService service = new NumberStatisticsService();
    private final NumberFileReader reader = new NumberFileReader();

    @Test
    void testValidLocaleNumbersAndStats() throws InvalidLocaleNumberException {
        List<String> lines = List.of(
                "en-US;10.5",
                "ru-RU;20,5",
                "de-DE;30,0"
        );
        List<LocaleNumberEntry> entries = parser.parseLines(lines);
        assertEquals(3, entries.size());
        assertEquals(new BigDecimal("61.0"), service.calculateSum(entries));
        assertEquals(new BigDecimal("20.333333"), service.calculateAverage(entries));
    }

    @Test
    void testInvalidNumberThrowsCustomException() {
        assertThrows(InvalidLocaleNumberException.class, () ->
                parser.parseLines(List.of("en-US;abc"))
        );
    }

    @Test
    void testMissingRecordThrowsCustomException() {
        assertThrows(InvalidLocaleNumberException.class, () ->
                parser.parseLines(List.of("en-US"))
        );
    }

    @Test
    void testOutOfBoundsThrowsCustomException() {
        assertThrows(InvalidLocaleNumberException.class, () ->
                parser.parseLines(List.of("en-US;1e400"))
        );
    }

    @Test
    void testMissingFileThrowsCustomException() {
        assertThrows(InvalidLocaleNumberException.class, () ->
                reader.readLines("data/nonexistent_file_12345.txt")
        );
    }
}
