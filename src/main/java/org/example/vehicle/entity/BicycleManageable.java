package org.example.vehicle.entity;

public interface BicycleManageable extends Manageable {
    int getGearsCount();
    void setGearsCount(int gearsCount);
    void adjustSeatHeight();
}
