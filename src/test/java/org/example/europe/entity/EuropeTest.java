package org.example.europe.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EuropeTest {

    @Test
    void testInnerClassCreationAndContext() {
        Europe europe = new Europe("Европа");
        Europe.TerritorialChange change = europe.new TerritorialChange(
                843, "Верденский договор", "Западно-Франкское королевство", 450000.0, "Образовано"
        );
        europe.addChange(change);

        assertEquals(1, europe.getHistory().size());
        assertEquals("Европа", change.getContinentContext());
        assertEquals(843, change.getYear());
    }

    @Test
    void testInvalidInnerClassArguments() {
        Europe europe = new Europe("Европа");
        assertThrows(IllegalArgumentException.class, () ->
                europe.new TerritorialChange(-50, "Событие", "Государство", 1000, "Статус")
        );
        assertThrows(IllegalArgumentException.class, () ->
                europe.new TerritorialChange(1900, "Событие", "Государство", -10, "Статус")
        );
        assertThrows(IllegalArgumentException.class, () ->
                europe.new TerritorialChange(1900, "Событие", "", 1000, "Статус")
        );
    }
}
