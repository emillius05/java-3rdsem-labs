package org.example.vehicle.entity;

public interface Manageable {
    String getRegNumber();
    void setRegNumber(String regNumber);
    String getBrand();
    void setBrand(String brand);
    String getModel();
    void setModel(String model);
    String getVin();
    void setVin(String vin);
    String getOwner();
    void setOwner(String owner);
    String getPropulsionType();
    void setPropulsionType(String propulsionType);
    void refuel();
    void repair();
    void service();
    void passInspection();
}
