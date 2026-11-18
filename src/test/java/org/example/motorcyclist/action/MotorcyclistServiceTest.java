package org.example.motorcyclist.action;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Boots;
import org.example.motorcyclist.entity.Gloves;
import org.example.motorcyclist.entity.Helmet;
import org.example.motorcyclist.entity.Motorcyclist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты сервиса: экипировка, стоимость, сортировка по весу, поиск по цене.
 */
public class MotorcyclistServiceTest {
    private MotorcyclistService service;
    private Motorcyclist motorcyclist;
    private Helmet helmet;
    private Gloves gloves;
    private Boots boots;

    @BeforeEach
    public void setUp() {
        service = new MotorcyclistService();
        motorcyclist = new Motorcyclist("Иван");
        helmet = new Helmet("Шлем", 1.2, 200.0, 4);
        gloves = new Gloves("Перчатки", 0.3, 50.0, false);
        boots = new Boots("Ботинки", 2.0, 100.0, 42);
    }

    @Test
    public void testEquip() {
        service.equip(motorcyclist, List.of(helmet, gloves));

        assertEquals(2, motorcyclist.getEquipment().size());
    }

    @Test
    public void testEquipSkipsNullItems() {
        service.equip(motorcyclist, Arrays.asList(helmet, null, gloves));

        assertEquals(2, motorcyclist.getEquipment().size());
    }

    @Test
    public void testEquipNullListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.equip(motorcyclist, null);
        });
    }

    @Test
    public void testTotalCost() {
        service.equip(motorcyclist, List.of(helmet, gloves, boots));

        assertEquals(350.0, service.totalCost(motorcyclist), 1e-9);
    }

    @Test
    public void testTotalCostEmptyEquipment() {
        assertEquals(0.0, service.totalCost(motorcyclist), 1e-9);
    }

    @Test
    public void testSortByWeight() {
        service.equip(motorcyclist, List.of(boots, helmet, gloves));

        List<Ammunition> sorted = service.sortByWeight(motorcyclist);

        assertEquals(3, sorted.size());
        assertSame(gloves, sorted.get(0));
        assertSame(helmet, sorted.get(1));
        assertSame(boots, sorted.get(2));
    }

    @Test
    public void testSortByWeightDoesNotChangeOriginalOrder() {
        service.equip(motorcyclist, List.of(boots, helmet, gloves));

        service.sortByWeight(motorcyclist);

        assertSame(boots, motorcyclist.getEquipment().get(0));
    }

    @Test
    public void testSortByWeightEmptyEquipment() {
        assertTrue(service.sortByWeight(motorcyclist).isEmpty());
    }

    @Test
    public void testFindByPriceRange() {
        service.equip(motorcyclist, List.of(helmet, gloves, boots));

        List<Ammunition> found = service.findByPriceRange(motorcyclist, 60.0, 250.0);

        assertEquals(2, found.size());
        assertSame(helmet, found.get(0));
        assertSame(boots, found.get(1));
    }

    @Test
    public void testFindByPriceRangeBoundsAreInclusive() {
        service.equip(motorcyclist, List.of(helmet, gloves, boots));

        List<Ammunition> found = service.findByPriceRange(motorcyclist, 50.0, 100.0);

        assertEquals(2, found.size());
        assertSame(gloves, found.get(0));
        assertSame(boots, found.get(1));
    }

    @Test
    public void testFindByPriceRangeNothingFound() {
        service.equip(motorcyclist, List.of(helmet, gloves, boots));

        assertTrue(service.findByPriceRange(motorcyclist, 1000.0, 2000.0).isEmpty());
    }

    @Test
    public void testFindByPriceRangeInvalidRangeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.findByPriceRange(motorcyclist, 300.0, 100.0);
        });
    }
}