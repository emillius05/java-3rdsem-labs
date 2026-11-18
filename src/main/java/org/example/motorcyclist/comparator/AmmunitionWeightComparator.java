package org.example.motorcyclist.comparator;

import org.example.motorcyclist.entity.Ammunition;

import java.util.Comparator;

/**
 * сравнивает амуницию по весу, от легкой к тяжелой.
 */
public class AmmunitionWeightComparator implements Comparator<Ammunition> {

    @Override
    public int compare(Ammunition first, Ammunition second) {
        return Double.compare(first.getWeight(), second.getWeight());
    }
}