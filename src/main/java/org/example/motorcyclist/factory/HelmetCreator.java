package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Helmet;

/**
 * создает шлемы, доп параметр это класс защиты.
 */
public class HelmetCreator extends AmmunitionCreator {

    @Override
    public Ammunition create(String name, double weight, double price, String extra) {
        return new Helmet(name, weight, price, parseInt(extra));
    }
}