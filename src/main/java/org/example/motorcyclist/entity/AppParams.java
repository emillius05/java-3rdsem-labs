package org.example.motorcyclist.entity;

/**
 * параметры запуска: имя мотоциклиста и диапазон цен для поиска, только хранение.
 */
public class AppParams {
    private final String motorcyclistName;
    private final double minPrice;
    private final double maxPrice;

    public AppParams(String motorcyclistName, double minPrice, double maxPrice) {
        this.motorcyclistName = motorcyclistName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getMotorcyclistName() {
        return motorcyclistName;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }
}