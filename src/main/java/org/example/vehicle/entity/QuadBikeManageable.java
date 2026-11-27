package org.example.vehicle.entity;

public interface QuadBikeManageable extends Manageable {
    int getEngineCapacity();
    void setEngineCapacity(int engineCapacity);
    void engageFourWheelDrive();
}
