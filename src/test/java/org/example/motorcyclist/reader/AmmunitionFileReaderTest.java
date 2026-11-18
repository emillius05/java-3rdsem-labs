package org.example.motorcyclist.reader;

import org.example.motorcyclist.entity.Ammunition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты чтения файла: нормальный файл, кривые строки, комментарии, нет файла.
 */
public class AmmunitionFileReaderTest {
    private AmmunitionFileReader reader;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        reader = new AmmunitionFileReader();
    }

    @Test
    public void testReadValidFile() throws IOException {
        Path file = tempDir.resolve("valid.txt");
        Files.write(file, List.of(
                "helmet;Shoei;1.4;450;5",
                "boots;TCX;1.6;230;42"));

        List<Ammunition> result = reader.read(file.toString());

        assertEquals(2, result.size());
        assertEquals("Shoei", result.get(0).getName());
        assertEquals("TCX", result.get(1).getName());
    }

    @Test
    public void testInvalidLinesAreIgnored() throws IOException {
        Path file = tempDir.resolve("mixed.txt");
        Files.write(file, List.of(
                "helmet;Shoei;1.4;450;5",
                "helmet;Брак;-1.2;100;4",
                "gloves;Перчатки;0.4;abc;true",
                "visor;Неизвестный;0.1;10;1",
                "просто мусор",
                "jacket;Dainese;2.8;520;true"));

        List<Ammunition> result = reader.read(file.toString());

        assertEquals(2, result.size());
        assertEquals("Shoei", result.get(0).getName());
        assertEquals("Dainese", result.get(1).getName());
    }

    @Test
    public void testCommentsAndBlankLinesAreSkipped() throws IOException {
        Path file = tempDir.resolve("comments.txt");
        Files.write(file, List.of(
                "# комментарий",
                "",
                "   ",
                "  # комментарий с отступом",
                "gloves;Held;0.35;120;false"));

        List<Ammunition> result = reader.read(file.toString());

        assertEquals(1, result.size());
        assertEquals("Held", result.get(0).getName());
    }

    @Test
    public void testEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.write(file, List.of());

        assertTrue(reader.read(file.toString()).isEmpty());
    }

    @Test
    public void testMissingFileReturnsEmptyList() {
        List<Ammunition> result = reader.read(tempDir.resolve("no-such-file.txt").toString());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDirectoryInsteadOfFileReturnsEmptyList() {
        List<Ammunition> result = reader.read(tempDir.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testInvalidPathReturnsEmptyList() {
        List<Ammunition> result = reader.read("bad\u0000path");
        assertTrue(result.isEmpty());
    }
}