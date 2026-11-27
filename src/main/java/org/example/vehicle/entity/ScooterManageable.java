package org.example.vehicle.entity;

public interface ScooterManageable extends Manageable {
    double getMaxSpeed();
    void setMaxSpeed(double maxSpeed);
    void unfoldScooter();
}
