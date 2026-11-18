package org.example.motorcyclist.entity;

/**
 * перчи, доп хранят признак водонепрониц
 */
public class Gloves extends Ammunition {
    private final boolean waterproof;

    public Gloves(String name, double weight, double price, boolean waterproof) {
        super(name, weight, price);
        this.waterproof = waterproof;
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    @Override
    public String getType() {
        return "Gloves";
    }
}