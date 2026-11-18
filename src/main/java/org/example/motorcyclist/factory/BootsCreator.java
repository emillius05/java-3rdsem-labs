package org.example.motorcyclist.factory;

import org.example.motorcyclist.entity.Ammunition;
import org.example.motorcyclist.entity.Boots;

/**
 * создает боты, доп параметр это размер.
 */
public class BootsCreator extends AmmunitionCreator {

    @Override
    public Ammunition create(String name, double weight, double price, String extra) {
        return new Boots(name, weight, price, parseInt(extra));
    }
}