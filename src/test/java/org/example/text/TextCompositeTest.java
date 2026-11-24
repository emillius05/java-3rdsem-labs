package org.example.text;

import org.example.text.composite.ComponentType;
import org.example.text.composite.SymbolLeaf;
import org.example.text.composite.TextComponent;
import org.example.text.io.TextFileManager;
import org.example.text.parser.AbstractTextParser;
import org.example.text.parser.TextParserChainBuilder;
import org.example.text.service.TextProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {
    private AbstractTextParser parser;
    private TextProcessingService service;

    @BeforeEach
    void setUp() {
        parser = TextParserChainBuilder.buildChain();
        service = new TextProcessingService();
    }

    @Test
    void testCompositeHierarchyAndNormalization() {
        String raw = "\tКласс   Java\t\tработает   быстро!  Второй   абзац.";
        TextComponent text = parser.parse(raw);

        assertEquals(ComponentType.TEXT, text.getType());
        assertEquals(1, text.getChildren().size());
        assertEquals(2, text.getChildren().get(0).getChildren().size());
        assertEquals("Класс Java работает быстро! Второй абзац.", service.normalizeWhitespace(text));
    }

    @Test
    void testVariant7SortWordsByVowelRatio() {
        // сток: 1/4 = 0.25; код: 1/3 = 0.33; окно: 2/4 = 0.50; идея: 3/4 = 0.75
        String sample = "Идея, окно, код, сток!";
        TextComponent parsed = parser.parse(sample);
        List<String> sorted = service.sortWordsByVowelRatio(parsed);

        assertEquals(List.of("сток", "код", "окно", "Идея"), sorted);
    }

    @Test
    void testFileReadAndWrite(@TempDir Path tempDir) throws IOException {
        TextFileManager manager = new TextFileManager();
        String raw = manager.readText("data/input.txt");
        TextComponent parsed = parser.parse(raw);
        List<String> sorted = service.sortWordsByVowelRatio(parsed);

        Path outFile = tempDir.resolve("result.txt");
        manager.writeText(outFile.toString(), String.join(", ", sorted));

        assertTrue(Files.exists(outFile));
        String written = Files.readString(outFile);
        assertFalse(written.isBlank());
    }

    @Test
    void testInvalidInputsAndLeafConstraints() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("   "));
        assertThrows(IllegalArgumentException.class, () -> service.sortWordsByVowelRatio(null));
        SymbolLeaf leaf = new SymbolLeaf('A', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(leaf));
    }
}
