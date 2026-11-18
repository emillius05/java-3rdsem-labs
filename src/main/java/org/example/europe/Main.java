package org.example.europe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.europe.action.EuropeHistoryService;
import org.example.europe.action.HistoryFilter;
import org.example.europe.comparator.YearComparator;
import org.example.europe.entity.Europe;
import org.example.europe.parser.TerritorialChangeParser;
import org.example.europe.reader.HistoryFileReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_PATH = "data/territorial_history.txt";

    public static void main(String[] args) {
        logger.info("=== Запуск приложения: История территориального деления Европы ===");

        Europe europe = new Europe("Европа");
        HistoryFileReader reader = new HistoryFileReader();
        TerritorialChangeParser parser = new TerritorialChangeParser();
        EuropeHistoryService service = new EuropeHistoryService();

        try {
            List<String> lines = reader.readLines(DATA_PATH);
            parser.parseAndPopulate(europe, lines);
        } catch (IOException e) {
            logger.error("Критическая ошибка чтения данных: {}", e.getMessage());
            return;
        }

        // 1. Использование внутреннего класса напрямую
        Europe.TerritorialChange extraChange = europe.new TerritorialChange(
                1990, "Объединение Германии", "ФРГ", 357022.0, "Объединено"
        );
        europe.addChange(extraChange);

        logger.info("--- Все записи (отсортировано по году через YearComparator) ---");
        List<Europe.TerritorialChange> byYear = service.sortChanges(europe, new YearComparator());
        byYear.forEach(c -> logger.info(c.toString()));

        // 2. Демонстрация АНОНИМНОГО КЛАССА №1 (фильтрация событий XX века)
        HistoryFilter twentiethCenturyFilter = new HistoryFilter() {
            @Override
            public boolean test(Europe.TerritorialChange change) {
                return change.getYear() >= 1901 && change.getYear() <= 2000;
            }
        };

        logger.info("--- События XX века (через анонимный класс HistoryFilter) ---");
        List<Europe.TerritorialChange> xxCentury = service.filterChanges(europe, twentiethCenturyFilter);
        xxCentury.forEach(c -> logger.info(c.toString()));

        // 3. Демонстрация АНОНИМНОГО КЛАССА №2 (сортировка по площади по убыванию)
        Comparator<Europe.TerritorialChange> areaDescComparator = new Comparator<Europe.TerritorialChange>() {
            @Override
            public int compare(Europe.TerritorialChange o1, Europe.TerritorialChange o2) {
                return Double.compare(o2.getAreaSqKm(), o1.getAreaSqKm());
            }
        };

        logger.info("--- Топ государств по площади (через анонимный Comparator) ---");
        List<Europe.TerritorialChange> byAreaDesc = service.sortChanges(europe, areaDescComparator);
        byAreaDesc.forEach(c -> logger.info(c.toString()));

        logger.info("=== Работа программы успешно завершена ===");
    }
}
