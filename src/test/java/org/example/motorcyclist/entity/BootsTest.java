package org.example.motorcyclist.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты ботинок: создание, границы размера, ошибки.
 */
public class BootsTest {

    @Test
    public void testValidBootsCreation() {
        Boots boots = new Boots("TCX", 1.6, 230.0, 42);
        assertEquals("TCX", boots.getName());
        assertEquals(1.6, boots.getWeight(), 1e-9);
        assertEquals(230.0, boots.getPrice(), 1e-9);
        assertEquals(42, boots.getSize());
        assertEquals("Boots", boots.getType());
    }

    @Test
    public void testSizeBounds() {
        assertEquals(30, new Boots("Мин", 1.0, 10.0, 30).getSize());
        assertEquals(50, new Boots("Макс", 1.0, 10.0, 50).getSize());
    }

    @Test
    public void testSizeTooSmallThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Boots("Тест", 1.0, 10.0, 29);
        });
    }

    @Test
    public void testSizeTooBigThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Boots("Тест", 1.0, 10.0, 51);
        });
    }

    @Test
    public void testIsInstanceOfAmmunition() {
        Boots boots = new Boots("Sidi", 2.4, 390.0, 44);
        assertInstanceOf(Ammunition.class, boots);
    }
}