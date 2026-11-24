package org.example.strings;

import org.example.strings.reader.TextFileReader;
import org.example.strings.service.FileMaskMatcherService;
import org.example.strings.service.TextCleanerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringTasksTest {
    private TextCleanerService cleanerService;
    private FileMaskMatcherService maskService;

    @BeforeEach
    void setUp() {
        cleanerService = new TextCleanerService();
        maskService = new FileMaskMatcherService();
    }

    @Test
    void testVariantACleanTextBothWays() {
        String input = "Привет, мир! 123 Java-8 & Stream_API... Тест7!";
        String expected = "Привет мир Java Stream API Тест";

        String actualString = cleanerService.cleanTextWithString(input);
        String actualStream = cleanerService.cleanTextWithStream(input);

        assertEquals(expected, actualString);
        assertEquals(expected, actualStream);
    }

    @Test
    void testVariantAOnlySymbolsAndEmpty() {
        assertEquals("", cleanerService.cleanTextWithString("12345 !@# $%^"));
        assertEquals("", cleanerService.cleanTextWithStream("12345 !@# $%^"));
        assertThrows(IllegalArgumentException.class, () -> cleanerService.cleanTextWithString(null));
        assertThrows(IllegalArgumentException.class, () -> cleanerService.cleanTextWithStream(null));
    }

    @Test
    void testVariantCFileMaskBothWays() {
        assertTrue(maskService.matchesMaskWithString("report_2026_final.txt", "report_*.txt"));
        assertTrue(maskService.matchesMaskWithStream("report_2026_final.txt", "report_*.txt"));

        assertTrue(maskService.matchesMaskWithString("doc_a.pdf", "doc_?.pdf"));
        assertTrue(maskService.matchesMaskWithStream("doc_a.pdf", "doc_?.pdf"));

        assertFalse(maskService.matchesMaskWithString("doc_ab.pdf", "doc_?.pdf"));
        assertFalse(maskService.matchesMaskWithStream("doc_ab.pdf", "doc_?.pdf"));

        assertTrue(maskService.matchesMaskWithString("file[1]+(test).log", "file[?]+(*).log"));
        assertTrue(maskService.matchesMaskWithStream("file[1]+(test).log", "file[?]+(*).log"));
    }

    @Test
    void testVariantCInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> maskService.matchesMaskWithString("", "*.txt"));
        assertThrows(IllegalArgumentException.class, () -> maskService.matchesMaskWithStream("a.txt", null));
    }

    @Test
    void testFileReader() throws IOException {
        TextFileReader reader = new TextFileReader();
        List<String> lines = reader.readLines("data/input.txt");
        assertEquals(4, lines.size());
    }
}
