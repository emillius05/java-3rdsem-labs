package org.example.motorcyclist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.motorcyclist.action.MotorcyclistService;
import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.AppParams;
import org.example.motorcyclist.entity.Motorcyclist;
import org.example.motorcyclist.reader.AmmunitionFileReader;
import org.example.motorcyclist.reader.ParamsFileReader;

import java.util.List;

/**
 * точка входа: читает файлы, экипирует мотоциклиста и пишет результаты в лог.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DEFAULT_FILE = "data/ammunition.txt";
    private static final String DEFAULT_PARAMS_FILE = "data/params.txt";

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : DEFAULT_FILE;
        String paramsPath = args.length > 1 ? args[1] : DEFAULT_PARAMS_FILE;

        AmmunitionFileReader reader = new AmmunitionFileReader();
        ParamsFileReader paramsReader = new ParamsFileReader();
        MotorcyclistService service = new MotorcyclistService();

        AppParams params = paramsReader.read(paramsPath);
        List<Ammunition> items = reader.read(filePath);
        Motorcyclist motorcyclist = new Motorcyclist(params.getMotorcyclistName());
        service.equip(motorcyclist, items);

        logger.info("Общая стоимость экипировки: {}", service.totalCost(motorcyclist));

        logger.info("Амуниция по возрастанию веса:");
        for (Ammunition item : service.sortByWeight(motorcyclist)) {
            logger.info("{}", item);
        }

        logger.info("Амуниция стоимостью от {} до {}:",
                params.getMinPrice(), params.getMaxPrice());
        for (Ammunition item : service.findByPriceRange(
                motorcyclist, params.getMinPrice(), params.getMaxPrice())) {
            logger.info("{}", item);
        }
    }
}