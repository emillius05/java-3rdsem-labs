package org.example.motorcyclist.reader;

import org.example.motorcyclist.entity.AppParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты чтения параметров: нормальный файл, кривые значения, нет файла.
 */
public class ParamsFileReaderTest {
    private ParamsFileReader reader;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        reader = new ParamsFileReader();
    }

    private AppParams readLines(String... lines) throws IOException {
        Path file = tempDir.resolve("params.txt");
        Files.write(file, List.of(lines));
        return reader.read(file.toString());
    }

    private void assertDefaults(AppParams params) {
        assertEquals("Иван", params.getMotorcyclistName());
        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(300.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testReadValidFile() throws IOException {
        AppParams params = readLines("name=Пётр", "minPrice=50", "maxPrice=200");

        assertEquals("Пётр", params.getMotorcyclistName());
        assertEquals(50.0, params.getMinPrice(), 1e-9);
        assertEquals(200.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testValuesAreTrimmed() throws IOException {
        AppParams params = readLines("# комментарий", "name=Пётр   ",
                "minPrice=50.5   ", "maxPrice=200   ");

        assertEquals("Пётр", params.getMotorcyclistName());
        assertEquals(50.5, params.getMinPrice(), 1e-9);
        assertEquals(200.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testMissingFileReturnsDefaults() {
        AppParams params = reader.read(tempDir.resolve("no-such-file.txt").toString());
        assertDefaults(params);
    }

    @Test
    public void testInvalidPathReturnsDefaults() {
        assertDefaults(reader.read("bad\u0000path"));
    }

    @Test
    public void testMalformedFileReturnsDefaults() throws IOException {
        assertDefaults(readLines("name=\\uZZZZ"));
    }

    @Test
    public void testMissingNameUsesDefault() throws IOException {
        AppParams params = readLines("minPrice=50", "maxPrice=200");

        assertEquals("Иван", params.getMotorcyclistName());
        assertEquals(50.0, params.getMinPrice(), 1e-9);
    }

    @Test
    public void testBlankNameUsesDefault() throws IOException {
        AppParams params = readLines("name=   ", "minPrice=50", "maxPrice=200");

        assertEquals("Иван", params.getMotorcyclistName());
    }

    @Test
    public void testMissingPricesUseDefaults() throws IOException {
        AppParams params = readLines("name=Пётр");

        assertEquals("Пётр", params.getMotorcyclistName());
        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(300.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testNotNumberPriceUsesDefault() throws IOException {
        AppParams params = readLines("name=Пётр", "minPrice=abc", "maxPrice=400");

        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(400.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testNegativePriceUsesDefault() throws IOException {
        AppParams params = readLines("name=Пётр", "minPrice=-5", "maxPrice=250");

        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(250.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testNaNPriceUsesDefault() throws IOException {
        AppParams params = readLines("name=Пётр", "minPrice=NaN", "maxPrice=250");

        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(250.0, params.getMaxPrice(), 1e-9);
    }

    @Test
    public void testMinGreaterThanMaxUsesDefaults() throws IOException {
        AppParams params = readLines("name=Пётр", "minPrice=500", "maxPrice=100");

        assertEquals("Пётр", params.getMotorcyclistName());
        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(300.0, params.getMaxPrice(), 1e-9);
    }
}