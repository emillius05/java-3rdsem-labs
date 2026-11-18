package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Boots;
import org.example.motorcyclist.entity.Gloves;
import org.example.motorcyclist.entity.Helmet;
import org.example.motorcyclist.entity.Jacket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты всех четырех creator: создание объектов и кривые доп параметры.
 */
public class AmmunitionCreatorsTest {

    @Test
    public void testHelmetCreatorCreatesHelmet() {
        Ammunition result = new HelmetCreator().create("Shoei", 1.4, 450.0, "5");
        assertInstanceOf(Helmet.class, result);
        assertEquals("Shoei", result.getName());
        assertEquals(5, ((Helmet) result).getProtectionClass());
    }

    @Test
    public void testHelmetCreatorTrimsExtra() {
        Ammunition result = new HelmetCreator().create("Shoei", 1.4, 450.0, " 4 ");
        assertEquals(4, ((Helmet) result).getProtectionClass());
    }

    @Test
    public void testHelmetCreatorNotNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HelmetCreator().create("Shoei", 1.4, 450.0, "abc");
        });
    }

    @Test
    public void testHelmetCreatorNullExtraThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HelmetCreator().create("Shoei", 1.4, 450.0, null);
        });
    }

    @Test
    public void testHelmetCreatorBadClassThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HelmetCreator().create("Shoei", 1.4, 450.0, "9");
        });
    }

    @Test
    public void testJacketCreatorCreatesJacket() {
        Ammunition result = new JacketCreator().create("Dainese", 2.8, 520.0, "true");
        assertInstanceOf(Jacket.class, result);
        assertTrue(((Jacket) result).hasArmor());
    }

    @Test
    public void testJacketCreatorFalseCaseInsensitive() {
        Ammunition result = new JacketCreator().create("Textile", 1.9, 260.0, " FALSE ");
        assertFalse(((Jacket) result).hasArmor());
    }

    @Test
    public void testJacketCreatorBadBooleanThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new JacketCreator().create("Куртка", 2.0, 200.0, "возможно");
        });
    }

    @Test
    public void testJacketCreatorNullExtraThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new JacketCreator().create("Куртка", 2.0, 200.0, null);
        });
    }

    @Test
    public void testGlovesCreatorCreatesGloves() {
        Ammunition result = new GlovesCreator().create("Held", 0.5, 150.0, "true");
        assertInstanceOf(Gloves.class, result);
        assertTrue(((Gloves) result).isWaterproof());
    }

    @Test
    public void testGlovesCreatorBadBooleanThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GlovesCreator().create("Held", 0.5, 150.0, "да");
        });
    }

    @Test
    public void testBootsCreatorCreatesBoots() {
        Ammunition result = new BootsCreator().create("TCX", 1.6, 230.0, "42");
        assertInstanceOf(Boots.class, result);
        assertEquals(42, ((Boots) result).getSize());
    }

    @Test
    public void testBootsCreatorBadSizeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BootsCreator().create("TCX", 1.6, 230.0, "60");
        });
    }

    @Test
    public void testBootsCreatorNotNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BootsCreator().create("TCX", 1.6, 230.0, "big");
        });
    }
}