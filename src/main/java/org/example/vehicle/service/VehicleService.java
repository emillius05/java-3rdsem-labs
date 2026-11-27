package org.example.vehicle.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.vehicle.entity.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleService {
    private static final Logger logger = LogManager.getLogger(VehicleService.class);

    public void performMaintenanceOnAll(List<Vehicle> vehicles) {
        logger.info("Выполнение обслуживания для всего автопарка");
        for (Vehicle v : vehicles) {
            v.service();
            v.passInspection();
        }
    }

    public List<Vehicle> sortByBrand(List<Vehicle> vehicles) {
        logger.info("Сортировка транспорта по бренду");
        return vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::getBrand))
                .collect(Collectors.toList());
    }

    public List<Vehicle> filterByPropulsion(List<Vehicle> vehicles, String propulsion) {
        logger.info("Фильтрация транспорта по типу движущей силы: {}", propulsion);
        return vehicles.stream()
                .filter(v -> v.getPropulsionType().equalsIgnoreCase(propulsion))
                .collect(Collectors.toList());
    }
}
