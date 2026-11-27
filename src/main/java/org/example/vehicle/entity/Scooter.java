package org.example.vehicle.entity;

public class Scooter extends Vehicle implements ScooterManageable {
    private double maxSpeed;

    public Scooter(String regNumber, String brand, String model, String vin, String owner, String propulsionType, double maxSpeed) {
        super(regNumber, brand, model, vin, owner, propulsionType);
        this.maxSpeed = maxSpeed > 0 ? maxSpeed : 25.0;
    }

    @Override
    public double getMaxSpeed() { return maxSpeed; }

    @Override
    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }

    @Override
    public void unfoldScooter() {
        System.out.println("Самокат " + getBrand() + " сложен/разложен.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Самокат, макс. скорость: %.1f км/ч]", maxSpeed);
    }
}
