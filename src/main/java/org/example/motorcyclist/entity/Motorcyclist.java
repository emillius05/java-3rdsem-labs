package org.example.motorcyclist.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * мотоциклист и его экипировка,бизнес-логики здесь нет, только хранение.
 */
public class Motorcyclist {
    private final String name;
    private final List<Ammunition> equipment = new ArrayList<>();

    public Motorcyclist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя мотоциклиста не может быть пустым");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAmmunition(Ammunition ammunition) {
        if (ammunition == null) {
            throw new IllegalArgumentException("Амуниция не может быть null");
        }

        equipment.add(ammunition);
    }

    /**
     * Возвращает копию списка, чтобы внешний код не мог испортить экипировку.
     */
    public List<Ammunition> getEquipment() {
        return new ArrayList<>(equipment);
    }
}