package org.example.vehicle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.vehicle.entity.*;
import org.example.vehicle.parser.VehicleParser;
import org.example.vehicle.reader.VehicleFileReader;
import org.example.vehicle.service.VehicleService;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_PATH = "data/vehicles.txt";

    public static void main(String[] args) {
        logger.info("=== Запуск лабораторной работы 6: Средства передвижения ===");

        VehicleFileReader reader = new VehicleFileReader();
        VehicleParser parser = new VehicleParser();
        VehicleService service = new VehicleService();

        try {
            List<String> lines = reader.readLines(DATA_PATH);
            List<Vehicle> vehicles = parser.parseVehicles(lines);

            logger.info("--- Список всех транспортных средств ---");
            vehicles.forEach(v -> logger.info(v.toString()));

            logger.info("--- Обслуживание парка ---");
            service.performMaintenanceOnAll(vehicles);

            logger.info("--- Демонстрация специализированных методов через интерфейсы ---");
            for (Vehicle v : vehicles) {
                if (v instanceof CarManageable c) c.activateClimateControl();
                else if (v instanceof BicycleManageable b) b.adjustSeatHeight();
                else if (v instanceof ScooterManageable s) s.unfoldScooter();
                else if (v instanceof MotorcycleManageable m) m.checkChainTension();
                else if (v instanceof QuadBikeManageable q) q.engageFourWheelDrive();
            }

        } catch (IOException e) {
            logger.error("Ошибка ввода-вывода: {}", e.getMessage());
        }

        logger.info("=== Программа успешно завершена ===");
    }
}
