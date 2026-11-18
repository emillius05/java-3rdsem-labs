package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;

/**
 * creator из паттерна factory method, наследники решают какой объект создавать.
 */
public abstract class AmmunitionCreator {

    /**
     * фабричный метод, у каждого наследника своя реализация.
     */
    public abstract Ammunition create(String name, double weight, double price,
                                      String extra);

    /**
     * переводит строку в целое число, null не пропускает.
     */
    protected static int parseInt(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Не задан дополнительный параметр");
        }

        return Integer.parseInt(value.trim());
    }

    /**
     * переводит строку в boolean, принимает только true и false.
     */
    protected static boolean parseBoolean(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Не задан дополнительный параметр");
        }

        String trimmed = value.trim();
        if ("true".equalsIgnoreCase(trimmed)) {
            return true;
        }
        if ("false".equalsIgnoreCase(trimmed)) {
            return false;
        }
        throw new IllegalArgumentException("Ожидалось true или false, получено: " + value);
    }
}