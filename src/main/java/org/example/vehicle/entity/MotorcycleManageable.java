package org.example.vehicle.entity;

public interface MotorcycleManageable extends Manageable {
    int getEngineVolume();
    void setEngineVolume(int engineVolume);
    void checkChainTension();
}
