package org.example.motorcyclist.entity;

/**
 * куртка, доп хранит наличие защитных вставок (брони)
 */
public class Jacket extends Ammunition {
    private final boolean hasArmor;

    public Jacket(String name, double weight, double price, boolean hasArmor) {
        super(name, weight, price);
        this.hasArmor = hasArmor;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    @Override
    public String getType() {
        return "Jacket";
    }
}