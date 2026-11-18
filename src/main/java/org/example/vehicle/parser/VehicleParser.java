package org.example.vehicle.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.vehicle.entity.*;

import java.util.ArrayList;
import java.util.List;

public class VehicleParser {
    private static final Logger logger = LogManager.getLogger(VehicleParser.class);
    private static final String DELIMITER = ";";

    public List<Vehicle> parseVehicles(List<String> lines) {
        logger.info("Начало парсинга объектов транспорта из строк");
        List<Vehicle> vehicles = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length < 8) {
                logger.warn("Пропуск некорректной строки (мало полей): {}", line);
                continue;
            }
            try {
                String type = parts[0].trim().toLowerCase();
                String regNumber = parts[1].trim();
                String brand = parts[2].trim();
                String model = parts[3].trim();
                String vin = parts[4].trim();
                String owner = parts[5].trim();
                String propulsion = parts[6].trim();

                Vehicle vehicle = switch (type) {
                    case "car" -> new Car(regNumber, brand, model, vin, owner, propulsion, Integer.parseInt(parts[7].trim()));
                    case "bicycle" -> new Bicycle(regNumber, brand, model, vin, owner, propulsion, Integer.parseInt(parts[7].trim()));
                    case "scooter" -> new Scooter(regNumber, brand, model, vin, owner, propulsion, Double.parseDouble(parts[7].trim()));
                    case "motorcycle" -> new Motorcycle(regNumber, brand, model, vin, owner, propulsion, Integer.parseInt(parts[7].trim()));
                    case "quad" -> new QuadBike(regNumber, brand, model, vin, owner, propulsion, Integer.parseInt(parts[7].trim()));
                    default -> {
                        logger.warn("Неизвестный тип транспортного средства: {}", type);
                        yield null;
                    }
                };

                if (vehicle != null) {
                    vehicles.add(vehicle);
                    logger.debug("Успешно создан объект: {}", vehicle);
                }
            } catch (Exception e) {
                logger.error("Ошибка при парсинге строки [{}]: {}", line, e.getMessage());
            }
        }
        logger.info("Парсинг завершен. Создано объектов: {}", vehicles.size());
        return vehicles;
    }
}
