package org.example.motorcyclist.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.motorcyclist.comparator.AmmunitionWeightComparator;
import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Motorcyclist;

import java.util.ArrayList;
import java.util.List;

/**
 * вся логика лабы: экипировка, стоимость, сортировка по весу, поиск по цене.
 */
public class MotorcyclistService {
    private static final Logger logger = LogManager.getLogger(MotorcyclistService.class);

    /**
     * надевает на мотоциклиста все предметы из списка, null пропускает.
     */
    public void equip(Motorcyclist motorcyclist, List<Ammunition> items) {
        logger.info("Экипируем мотоциклиста {}", motorcyclist.getName());

        if (items == null) {
            throw new IllegalArgumentException("Список амуниции не может быть null");
        }

        for (Ammunition item : items) {
            if (item == null) {
                logger.warn("Пропущен пустой элемент амуниции");
                continue;
            }
            motorcyclist.addAmmunition(item);
        }

        logger.info("Экипировано предметов: {}", motorcyclist.getEquipment().size());
    }

    /**
     * считает общую стоимость всей экипировки.
     */
    public double totalCost(Motorcyclist motorcyclist) {
        logger.debug("Рассчитываем стоимость экипировки");

        double total = motorcyclist.getEquipment().stream()
                .mapToDouble(Ammunition::getPrice)
                .sum();

        logger.info("Стоимость экипировки: {}", total);
        return total;
    }

    /**
     * возвращает новый список, отсортированный по весу, сам мотоциклист не меняется.
     */
    public List<Ammunition> sortByWeight(Motorcyclist motorcyclist) {
        logger.debug("Сортируем амуницию по весу");

        List<Ammunition> sorted = new ArrayList<>(motorcyclist.getEquipment());
        sorted.sort(new AmmunitionWeightComparator());

        logger.info("Амуниция отсортирована по весу, предметов: {}", sorted.size());
        return sorted;
    }

    /**
     * ищет предметы с ценой от minPrice до maxPrice включительно.
     */
    public List<Ammunition> findByPriceRange(Motorcyclist motorcyclist,
                                             double minPrice, double maxPrice) {
        logger.info("Поиск амуниции с ценой от {} до {}", minPrice, maxPrice);

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException(
                    "Минимальная цена не может быть больше максимальной");
        }

        List<Ammunition> result = motorcyclist.getEquipment().stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .toList();

        logger.info("Поиск завершён, найдено предметов: {}", result.size());
        return result;
    }
}