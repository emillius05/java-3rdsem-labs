package org.example.motorcyclist.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты шлема: создание, границы класса защиты, ошибки в данных.
 */
public class HelmetTest {

    @Test
    public void testValidHelmetCreation() {
        Helmet helmet = new Helmet("Shoei", 1.4, 450.0, 5);
        assertEquals("Shoei", helmet.getName());
        assertEquals(1.4, helmet.getWeight(), 1e-9);
        assertEquals(450.0, helmet.getPrice(), 1e-9);
        assertEquals(5, helmet.getProtectionClass());
        assertEquals("Helmet", helmet.getType());
    }

    @Test
    public void testProtectionClassBounds() {
        assertEquals(1, new Helmet("Мин", 1.0, 10.0, 1).getProtectionClass());
        assertEquals(5, new Helmet("Макс", 1.0, 10.0, 5).getProtectionClass());
    }

    @Test
    public void testProtectionClassTooLowThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", 1.0, 10.0, 0);
        });
    }

    @Test
    public void testProtectionClassTooHighThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", 1.0, 10.0, 6);
        });
    }

    @Test
    public void testZeroPriceIsAllowed() {
        Helmet helmet = new Helmet("Подарок", 1.0, 0.0, 3);
        assertEquals(0.0, helmet.getPrice(), 1e-9);
    }

    @Test
    public void testNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", 1.0, -0.01, 3);
        });
    }

    @Test
    public void testZeroWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", 0.0, 10.0, 3);
        });
    }

    @Test
    public void testNegativeWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", -1.0, 10.0, 3);
        });
    }

    @Test
    public void testNaNWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", Double.NaN, 10.0, 3);
        });
    }

    @Test
    public void testInfinitePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("Тест", 1.0, Double.POSITIVE_INFINITY, 3);
        });
    }

    @Test
    public void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet(null, 1.0, 10.0, 3);
        });
    }

    @Test
    public void testBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Helmet("   ", 1.0, 10.0, 3);
        });
    }

    @Test
    public void testIsInstanceOfAmmunition() {
        Helmet helmet = new Helmet("AGV", 1.3, 180.0, 3);
        assertInstanceOf(Ammunition.class, helmet);
    }

    @Test
    public void testToStringFormat() {
        Helmet helmet = new Helmet("AGV", 1.3, 180.0, 3);
        String result = helmet.toString();
        assertTrue(result.contains("AGV"));
        assertTrue(result.contains("Helmet"));
    }
}