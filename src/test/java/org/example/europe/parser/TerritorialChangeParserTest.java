package org.example.europe.parser;

import org.example.europe.entity.Europe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TerritorialChangeParserTest {

    @Test
    void testParseValidAndInvalidLines() {
        Europe europe = new Europe("Европа");
        TerritorialChangeParser parser = new TerritorialChangeParser();

        List<String> rawLines = List.of(
                "1648;Вестфальский мир;Швейцария;41285.0;Признано",
                "bad_year;Событие;Страна;1000.0;Статус",
                "1900;Событие;Страна;-200.0;Статус",
                "incomplete_line;only_two_parts"
        );

        parser.parseAndPopulate(europe, rawLines);
        assertEquals(1, europe.getHistory().size());
        assertEquals("Швейцария", europe.getHistory().get(0).getStateName());
    }
}
