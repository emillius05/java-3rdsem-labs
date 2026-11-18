package org.example.motorcyclist.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты перчаток: водонепроницаемые и нет, ошибка в данных.
 */
public class GlovesTest {

    @Test
    public void testWaterproofGloves() {
        Gloves gloves = new Gloves("Held", 0.5, 150.0, true);
        assertEquals("Held", gloves.getName());
        assertEquals(0.5, gloves.getWeight(), 1e-9);
        assertEquals(150.0, gloves.getPrice(), 1e-9);
        assertTrue(gloves.isWaterproof());
        assertEquals("Gloves", gloves.getType());
    }

    @Test
    public void testNotWaterproofGloves() {
        Gloves gloves = new Gloves("Летние", 0.3, 80.0, false);
        assertFalse(gloves.isWaterproof());
    }

    @Test
    public void testNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gloves("Брак", 0.3, -5.0, false);
        });
    }

    @Test
    public void testIsInstanceOfAmmunition() {
        Gloves gloves = new Gloves("Перчатки", 0.4, 90.0, true);
        assertInstanceOf(Ammunition.class, gloves);
    }
}