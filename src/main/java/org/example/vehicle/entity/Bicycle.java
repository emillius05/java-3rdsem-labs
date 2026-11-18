package org.example.vehicle.entity;

public class Bicycle extends Vehicle {
    private int gearsCount;

    public Bicycle(String regNumber, String brand, String model, String vin, String owner, String propulsionType, int gearsCount) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.gearsCount = gearsCount > 0 ? gearsCount : 1;
    }

    public int getGearsCount() { return gearsCount; }
    public void setGearsCount(int gearsCount) { this.gearsCount = gearsCount; }

    public void adjustSeatHeight() {
        System.out.println("Высота седла велосипеда " + getBrand() + " отрегулирована.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Велосипед, передач: %d]", gearsCount);
    }
}
