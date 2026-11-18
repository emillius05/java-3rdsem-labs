package org.example.europe.action;

import org.example.europe.comparator.YearComparator;
import org.example.europe.entity.Europe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuropeHistoryServiceTest {
    private Europe europe;
    private EuropeHistoryService service;

    @BeforeEach
    void setUp() {
        europe = new Europe("Европа");
        service = new EuropeHistoryService();
        europe.addChange(europe.new TerritorialChange(1991, "Распад СССР", "Беларусь", 207600.0, "Независимость"));
        europe.addChange(europe.new TerritorialChange(843, "Верденский договор", "Западно-Франкское королевство", 450000.0, "Образовано"));
        europe.addChange(europe.new TerritorialChange(1993, "Бархатный развод", "Чехия", 78866.0, "Образовано"));
        europe.addChange(europe.new TerritorialChange(1993, "Бархатный развод", "Словакия", 49035.0, "Образовано"));
    }

    @Test
    void testAnonymousFilter() {
        List<Europe.TerritorialChange> filtered = service.filterChanges(europe, new HistoryFilter() {
            @Override
            public boolean test(Europe.TerritorialChange change) {
                return change.getYear() == 1993;
            }
        });
        assertEquals(2, filtered.size());
    }

    @Test
    void testAnonymousComparator() {
        List<Europe.TerritorialChange> sorted = service.sortChanges(europe, new Comparator<Europe.TerritorialChange>() {
            @Override
            public int compare(Europe.TerritorialChange o1, Europe.TerritorialChange o2) {
                return Double.compare(o1.getAreaSqKm(), o2.getAreaSqKm());
            }
        });
        assertEquals("Словакия", sorted.get(0).getStateName());
        assertEquals("Западно-Франкское королевство", sorted.get(3).getStateName());
    }

    @Test
    void testYearComparator() {
        List<Europe.TerritorialChange> sorted = service.sortChanges(europe, new YearComparator());
        assertEquals(843, sorted.get(0).getYear());
        assertEquals(1993, sorted.get(3).getYear());
    }

    @Test
    void testCalculateTotalAreaInYear() {
        double total1993 = service.calculateTotalAreaInYear(europe, 1993);
        assertEquals(127901.0, total1993, 0.001);
    }
}
