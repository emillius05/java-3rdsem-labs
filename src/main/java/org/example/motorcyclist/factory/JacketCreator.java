package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Jacket;

/**
 * создает куртки, доп параметр это наличие брони.
 */
public class JacketCreator extends AmmunitionCreator {

    @Override
    public Ammunition create(String name, double weight, double price, String extra) {
        return new Jacket(name, weight, price, parseBoolean(extra));
    }
}