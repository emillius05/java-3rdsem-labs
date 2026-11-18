package org.example.motorcyclist.parser;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Boots;
import org.example.motorcyclist.entity.Gloves;
import org.example.motorcyclist.entity.Helmet;
import org.example.motorcyclist.entity.Jacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты парсера строк: нормальные строки и все виды кривых данных.
 */
public class AmmunitionParserTest {
    private AmmunitionParser parser;

    @BeforeEach
    public void setUp() {
        parser = new AmmunitionParser();
    }

    @Test
    public void testParseHelmet() {
        Ammunition result = parser.parse("helmet;Shoei X-Spirit;1.45;450;5");
        assertInstanceOf(Helmet.class, result);
        assertEquals("Shoei X-Spirit", result.getName());
        assertEquals(1.45, result.getWeight(), 1e-9);
        assertEquals(450.0, result.getPrice(), 1e-9);
    }

    @Test
    public void testParseJacket() {
        assertInstanceOf(Jacket.class, parser.parse("jacket;Dainese;2.8;520;true"));
    }

    @Test
    public void testParseGloves() {
        assertInstanceOf(Gloves.class, parser.parse("gloves;Held;0.35;120;false"));
    }

    @Test
    public void testParseBoots() {
        assertInstanceOf(Boots.class, parser.parse("boots;TCX;1.6;230;42"));
    }

    @Test
    public void testParseTrimsSpaces() {
        Ammunition result = parser.parse("  helmet ; AGV K1 ; 1.3 ; 180 ; 3 ");
        assertEquals("AGV K1", result.getName());
        assertEquals(180.0, result.getPrice(), 1e-9);
    }

    @Test
    public void testParseNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(null);
        });
    }

    @Test
    public void testParseTooFewFieldsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("helmet;Без параметра;1.3;100");
        });
    }

    @Test
    public void testParseTooManyFieldsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("helmet;Шлем;1.3;100;3;лишнее");
        });
    }

    @Test
    public void testParseBadWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("helmet;Шлем;тяжёлый;100;3");
        });
    }

    @Test
    public void testParseBadPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("gloves;Перчатки;0.4;abc;true");
        });
    }

    @Test
    public void testParseNegativeWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("helmet;Брак;-1.2;100;4");
        });
    }

    @Test
    public void testParseUnknownTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("visor;Неизвестный;0.1;10;1");
        });
    }

    @Test
    public void testParseBadExtraThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("boots;Ботинки;1.8;150;60");
        });
    }
}