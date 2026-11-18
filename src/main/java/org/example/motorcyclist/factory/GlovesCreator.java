package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Gloves;

/**
 * создает перчи, доп параметр это водонепроницаемость.
 */
public class GlovesCreator extends AmmunitionCreator {

    @Override
    public Ammunition create(String name, double weight, double price, String extra) {
        return new Gloves(name, weight, price, parseBoolean(extra));
    }
}