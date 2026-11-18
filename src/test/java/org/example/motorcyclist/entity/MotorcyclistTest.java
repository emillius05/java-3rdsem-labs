package org.example.motorcyclist.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты мотоциклиста: добавление амуниции, ошибки, копия списка.
 */
public class MotorcyclistTest {
    private Motorcyclist motorcyclist;

    @BeforeEach
    public void setUp() {
        motorcyclist = new Motorcyclist("Иван");
    }

    @Test
    public void testValidCreation() {
        assertEquals("Иван", motorcyclist.getName());
        assertTrue(motorcyclist.getEquipment().isEmpty());
    }

    @Test
    public void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Motorcyclist(null);
        });
    }

    @Test
    public void testBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Motorcyclist("  ");
        });
    }

    @Test
    public void testAddAmmunition() {
        Helmet helmet = new Helmet("Shoei", 1.4, 450.0, 5);
        motorcyclist.addAmmunition(helmet);

        List<Ammunition> equipment = motorcyclist.getEquipment();
        assertEquals(1, equipment.size());
        assertSame(helmet, equipment.get(0));
    }

    @Test
    public void testAddMultipleKeepsOrder() {
        Helmet helmet = new Helmet("Shoei", 1.4, 450.0, 5);
        Boots boots = new Boots("TCX", 1.6, 230.0, 42);
        motorcyclist.addAmmunition(helmet);
        motorcyclist.addAmmunition(boots);

        List<Ammunition> equipment = motorcyclist.getEquipment();
        assertEquals(2, equipment.size());
        assertSame(helmet, equipment.get(0));
        assertSame(boots, equipment.get(1));
    }

    @Test
    public void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            motorcyclist.addAmmunition(null);
        });
    }

    @Test
    public void testGetEquipmentReturnsCopy() {
        motorcyclist.addAmmunition(new Helmet("Shoei", 1.4, 450.0, 5));

        List<Ammunition> list1 = motorcyclist.getEquipment();
        List<Ammunition> list2 = motorcyclist.getEquipment();
        assertNotSame(list1, list2);

        list1.clear();
        assertEquals(1, motorcyclist.getEquipment().size());
    }
}