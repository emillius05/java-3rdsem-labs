package org.example.motorcyclist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты main: файлы по умолчанию, нет файла, свои файлы, не должен падать.
 */
public class MainTest {

    @TempDir
    Path tempDir;

    @Test
    public void testMainWithDefaultFiles() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }

    @Test
    public void testMainWithMissingFile() {
        assertDoesNotThrow(() -> Main.main(new String[]{"no-such-file.txt"}));
    }

    @Test
    public void testMainWithCustomFiles() throws IOException {
        Path ammunition = tempDir.resolve("ammunition.txt");
        Path params = tempDir.resolve("params.txt");
        Files.write(ammunition, List.of("helmet;Shoei;1.4;450;5", "boots;TCX;1.6;230;42"));
        Files.write(params, List.of("name=Пётр", "minPrice=200", "maxPrice=500"));

        assertDoesNotThrow(() -> Main.main(
                new String[]{ammunition.toString(), params.toString()}));
    }
}