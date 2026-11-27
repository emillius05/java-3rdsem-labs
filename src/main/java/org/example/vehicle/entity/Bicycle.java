package org.example.vehicle.entity;

public class Bicycle extends Vehicle implements BicycleManageable {
    private int gearsCount;

    public Bicycle(String regNumber, String brand, String model, String vin, String owner, String propulsionType, int gearsCount) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.gearsCount = gearsCount > 0 ? gearsCount : 1;
    }

    @Override
    public int getGearsCount() { return gearsCount; }

    @Override
    public void setGearsCount(int gearsCount) { this.gearsCount = gearsCount; }

    @Override
    public void adjustSeatHeight() {
        System.out.println("Высота седла велосипеда " + getBrand() + " отрегулирована.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Велосипед, передач: %d]", gearsCount);
    }
}
