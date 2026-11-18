package org.example.motorcyclist.entity;

/**
 * базовый класс амуниции мотоциклиста
 */
public abstract class Ammunition {
    private final String name;
    private final double weight; // кг
    private final double price;  // $

    public Ammunition(String name, double weight, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("Вес должен быть положительным числом");
        }
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Цена должна быть неотрицательным числом");
        }

        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    /**
     * возвр назв типа амуниции
     */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s[Тип: %s, Вес: %.2f кг, Цена: %.2f$]",
                name, getType(), weight, price);
    }
}