package org.example.motorcyclist.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тест параметров запуска: значения хранятся как положили.
 */
public class AppParamsTest {

    @Test
    public void testGetters() {
        AppParams params = new AppParams("Иван", 100.0, 300.0);
        assertEquals("Иван", params.getMotorcyclistName());
        assertEquals(100.0, params.getMinPrice(), 1e-9);
        assertEquals(300.0, params.getMaxPrice(), 1e-9);
    }
}