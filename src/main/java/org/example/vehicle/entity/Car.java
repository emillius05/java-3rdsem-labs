package org.example.vehicle.entity;

public class Car extends Vehicle {
    private int doorsCount;

    public Car(String regNumber, String brand, String model, String vin, String owner, String propulsionType, int doorsCount) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.doorsCount = doorsCount > 0 ? doorsCount : 4;
    }

    public int getDoorsCount() { return doorsCount; }
    public void setDoorsCount(int doorsCount) { this.doorsCount = doorsCount; }

    public void activateClimateControl() {
        System.out.println("Климат-контроль автомобиля " + getBrand() + " активирован.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Автомобиль, дверей: %d]", doorsCount);
    }
}
