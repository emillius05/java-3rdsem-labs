package org.example.motorcyclist.comparator;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Boots;
import org.example.motorcyclist.entity.Gloves;
import org.example.motorcyclist.entity.Helmet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тесты компаратора: легче, тяжелее, равный вес и сортировка списка.
 */
public class AmmunitionWeightComparatorTest {
    private final AmmunitionWeightComparator comparator = new AmmunitionWeightComparator();

    @Test
    public void testLighterComesFirst() {
        Ammunition light = new Gloves("Лёгкие", 0.3, 50.0, false);
        Ammunition heavy = new Boots("Тяжёлые", 2.0, 50.0, 42);
        assertTrue(comparator.compare(light, heavy) < 0);
    }

    @Test
    public void testHeavierComesLast() {
        Ammunition light = new Gloves("Лёгкие", 0.3, 50.0, false);
        Ammunition heavy = new Boots("Тяжёлые", 2.0, 50.0, 42);
        assertTrue(comparator.compare(heavy, light) > 0);
    }

    @Test
    public void testEqualWeights() {
        Ammunition first = new Gloves("Первые", 1.0, 50.0, false);
        Ammunition second = new Helmet("Второй", 1.0, 80.0, 3);
        assertEquals(0, comparator.compare(first, second));
    }

    @Test
    public void testSortList() {
        Ammunition boots = new Boots("Ботинки", 2.0, 100.0, 42);
        Ammunition gloves = new Gloves("Перчатки", 0.3, 50.0, false);
        Ammunition helmet = new Helmet("Шлем", 1.2, 200.0, 4);

        List<Ammunition> list = new ArrayList<>(List.of(boots, gloves, helmet));
        list.sort(comparator);

        assertSame(gloves, list.get(0));
        assertSame(helmet, list.get(1));
        assertSame(boots, list.get(2));
    }
}