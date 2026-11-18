package org.example.vehicle;

import org.example.vehicle.entity.*;
import org.example.vehicle.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private List<Vehicle> vehicles;
    private VehicleService service;

    @BeforeEach
    void setUp() {
        vehicles = List.of(
                new Car("A123", "Toyota", "Camry", "VIN1", "Иван", "ДВС", 4),
                new Bicycle("B456", "Stels", "Navigator", "VIN2", "Петр", "Мускулы", 18)
        );
        service = new VehicleService();
    }

    @Test
    void testCarSpecificMethod() {
        Car car = (Car) vehicles.get(0);
        assertEquals(4, car.getDoorsCount());
        assertNotNull(car.getBrand());
    }

    @Test
    void testServiceFiltering() {
        List<Vehicle> dcs = service.filterByPropulsion(vehicles, "ДВС");
        assertEquals(1, dcs.size());
        assertEquals("Toyota", dcs.get(0).getBrand());
    }

    @Test
    void testInvalidVehicleCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("", "Brand", "Model", "VIN", "Owner", "Prop", 4);
        });
    }
}
