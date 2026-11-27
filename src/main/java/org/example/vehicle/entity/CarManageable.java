package org.example.vehicle.entity;

public interface CarManageable extends Manageable {
    int getDoorsCount();
    void setDoorsCount(int doorsCount);
    void activateClimateControl();
}
