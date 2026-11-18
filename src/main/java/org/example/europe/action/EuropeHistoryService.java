package org.example.europe.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.europe.entity.Europe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EuropeHistoryService {
    private static final Logger logger = LogManager.getLogger(EuropeHistoryService.class);

    public List<Europe.TerritorialChange> filterChanges(Europe europe, HistoryFilter filter) {
        logger.info("Фильтрация исторических записей для объекта: {}", europe.getContinentName());
        List<Europe.TerritorialChange> result = new ArrayList<>();
        for (Europe.TerritorialChange change : europe.getHistory()) {
            if (filter.test(change)) {
                result.add(change);
            }
        }
        logger.debug("Найдено записей после фильтрации: {}", result.size());
        return result;
    }

    public List<Europe.TerritorialChange> sortChanges(Europe europe, Comparator<Europe.TerritorialChange> comparator) {
        logger.info("Сортировка исторических записей");
        List<Europe.TerritorialChange> sorted = new ArrayList<>(europe.getHistory());
        sorted.sort(comparator);
        return sorted;
    }

    public double calculateTotalAreaInYear(Europe europe, int year) {
        logger.info("Подсчет суммарной площади государств за {} год", year);
        return europe.getHistory().stream()
                .filter(c -> c.getYear() == year)
                .mapToDouble(Europe.TerritorialChange::getAreaSqKm)
                .sum();
    }
}
