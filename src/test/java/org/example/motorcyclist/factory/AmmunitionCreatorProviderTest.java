package org.example.motorcyclist.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты выбора creator по строке: все типы, регистр, ошибки.
 */
public class AmmunitionCreatorProviderTest {

    @Test
    public void testHelmetCreator() {
        assertInstanceOf(HelmetCreator.class, AmmunitionCreatorProvider.getCreator("helmet"));
    }

    @Test
    public void testJacketCreator() {
        assertInstanceOf(JacketCreator.class, AmmunitionCreatorProvider.getCreator("jacket"));
    }

    @Test
    public void testGlovesCreator() {
        assertInstanceOf(GlovesCreator.class, AmmunitionCreatorProvider.getCreator("gloves"));
    }

    @Test
    public void testBootsCreator() {
        assertInstanceOf(BootsCreator.class, AmmunitionCreatorProvider.getCreator("boots"));
    }

    @Test
    public void testCaseInsensitiveAndTrimmed() {
        assertInstanceOf(HelmetCreator.class,
                AmmunitionCreatorProvider.getCreator("  HeLmEt "));
    }

    @Test
    public void testUnknownTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            AmmunitionCreatorProvider.getCreator("visor");
        });
    }

    @Test
    public void testNullTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            AmmunitionCreatorProvider.getCreator(null);
        });
    }
}