package org.example.motorcyclist.entity;

/**
 * шлем,доп хранит класс защиты от 1 до 5
 */
public class Helmet extends Ammunition {
    private static final int MIN_PROTECTION_CLASS = 1;
    private static final int MAX_PROTECTION_CLASS = 5;

    private final int protectionClass;

    public Helmet(String name, double weight, double price, int protectionClass) {
        super(name, weight, price);
        if (protectionClass < MIN_PROTECTION_CLASS
                || protectionClass > MAX_PROTECTION_CLASS) {
            throw new IllegalArgumentException("Класс защиты шлема должен быть от 1 до 5");
        }

        this.protectionClass = protectionClass;
    }

    public int getProtectionClass() {
        return protectionClass;
    }

    @Override
    public String getType() {
        return "Helmet";
    }
}