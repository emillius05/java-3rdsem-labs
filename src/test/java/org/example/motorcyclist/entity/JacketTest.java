package org.example.motorcyclist.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты куртки: с броней, без брони, ошибка в данных.
 */
public class JacketTest {

    @Test
    public void testJacketWithArmor() {
        Jacket jacket = new Jacket("Dainese", 2.8, 520.0, true);
        assertEquals("Dainese", jacket.getName());
        assertEquals(2.8, jacket.getWeight(), 1e-9);
        assertEquals(520.0, jacket.getPrice(), 1e-9);
        assertTrue(jacket.hasArmor());
        assertEquals("Jacket", jacket.getType());
    }

    @Test
    public void testJacketWithoutArmor() {
        Jacket jacket = new Jacket("Textile", 1.9, 260.0, false);
        assertFalse(jacket.hasArmor());
    }

    @Test
    public void testNegativeWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Jacket("Брак", -2.0, 100.0, true);
        });
    }

    @Test
    public void testIsInstanceOfAmmunition() {
        Jacket jacket = new Jacket("Куртка", 2.0, 200.0, true);
        assertInstanceOf(Ammunition.class, jacket);
    }
}