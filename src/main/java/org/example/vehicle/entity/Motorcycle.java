package org.example.vehicle.entity;

public class Motorcycle extends Vehicle implements MotorcycleManageable {
    private int engineVolume;

    public Motorcycle(String regNumber, String brand, String model, String vin, String owner, String propulsionType, int engineVolume) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.engineVolume = engineVolume > 0 ? engineVolume : 150;
    }

    @Override
    public int getEngineVolume() { return engineVolume; }

    @Override
    public void setEngineVolume(int engineVolume) { this.engineVolume = engineVolume; }

    @Override
    public void checkChainTension() {
        System.out.println("Натяжение цепи мотоцикла " + getBrand() + " проверено.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Мотоцикл, объем двигателя: %d куб.см]", engineVolume);
    }
}
