package org.example.motorcyclist.entity;

/**
 * боты, доп хранят размер от 30 до 50.
 */
public class Boots extends Ammunition {
    private static final int MIN_SIZE = 30;
    private static final int MAX_SIZE = 50;

    private final int size;

    public Boots(String name, double weight, double price, int size) {
        super(name, weight, price);
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("Размер ботинок должен быть от 30 до 50");
        }

        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String getType() {
        return "Boots";
    }
}