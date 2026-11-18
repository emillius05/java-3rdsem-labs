package org.example.vehicle.entity;

import java.util.Objects;

public abstract class Vehicle implements Manageable {
    private String regNumber;
    private String brand;
    private String model;
    private String vin;
    private String owner;
    private String propulsionType;

    public Vehicle(String regNumber, String brand, String model, String vin, String owner, String propulsionType) {
        if (regNumber == null || regNumber.isBlank()) throw new IllegalArgumentException("Некорректный рег. номер");
        if (vin == null || vin.isBlank()) throw new IllegalArgumentException("Некорректный VIN");
        this.regNumber = regNumber;
        this.brand = brand != null ? brand : "Unknown";
        this.model = model != null ? model : "Unknown";
        this.vin = vin;
        this.owner = owner != null ? owner : "No Owner";
        this.propulsionType = propulsionType != null ? propulsionType : "Unknown";
    }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public String getPropulsionType() { return propulsionType; }
    public void setPropulsionType(String propulsionType) { this.propulsionType = propulsionType; }

    @Override
    public void refuel() {
        System.out.println(brand + " " + model + " заправлено / заряжено.");
    }

    @Override
    public void repair() {
        System.out.println(brand + " " + model + " отремонтировано.");
    }

    @Override
    public void service() {
        System.out.println(brand + " " + model + " прошло техническое обслуживание.");
    }

    @Override
    public void passInspection() {
        System.out.println(brand + " " + model + " прошло техосмотр успешно.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(vin, vehicle.vin) && Objects.equals(regNumber, vehicle.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin, regNumber);
    }

    @Override
    public String toString() {
        return String.format("Транспорт [%s %s, Рег: %s, VIN: %s, Владелец: %s]", brand, model, regNumber, vin, owner);
    }
}
