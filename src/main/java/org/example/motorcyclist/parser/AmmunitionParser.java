package org.example.motorcyclist.parser;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.factory.AmmunitionCreator;
import org.example.motorcyclist.factory.AmmunitionCreatorProvider;

/**
 * превращает одну строку из файла в объект амуниции.
 */
public class AmmunitionParser {
    private static final String SEPARATOR = ";";
    private static final int FIELDS_COUNT = 5;

    /**
     * формат строки: тип;название;вес;цена;доп параметр.
     * если с данными что-то не так, бросает IllegalArgumentException.
     */
    public Ammunition parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        }

        String[] parts = line.split(SEPARATOR, -1);
        if (parts.length != FIELDS_COUNT) {
            throw new IllegalArgumentException(
                    "Ожидалось полей: " + FIELDS_COUNT + ", получено: " + parts.length);
        }

        String type = parts[0].trim();
        String name = parts[1].trim();
        double weight = Double.parseDouble(parts[2].trim());
        double price = Double.parseDouble(parts[3].trim());
        String extra = parts[4].trim();

        AmmunitionCreator creator = AmmunitionCreatorProvider.getCreator(type);
        return creator.create(name, weight, price, extra);
    }
}