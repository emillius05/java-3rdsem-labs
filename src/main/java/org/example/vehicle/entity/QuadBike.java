package org.example.vehicle.entity;

public class QuadBike extends Vehicle implements QuadBikeManageable {
    private int engineCapacity;

    public QuadBike(String regNumber, String brand, String model, String vin, String owner, String propulsionType, int engineCapacity) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.engineCapacity = engineCapacity > 0 ? engineCapacity : 500;
    }

    @Override
    public int getEngineCapacity() { return engineCapacity; }

    @Override
    public void setEngineCapacity(int engineCapacity) { this.engineCapacity = engineCapacity; }

    @Override
    public void engageFourWheelDrive() {
        System.out.println("Полный привод квадроцикла " + getBrand() + " задействован.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Квадроцикл, объем: %d куб.см]", engineCapacity);
    }
}
