package org.example.europe.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Europe {
    private String continentName;
    private final List<TerritorialChange> history;

    public Europe(String continentName) {
        this.continentName = (continentName == null || continentName.isBlank()) ? "Европа" : continentName;
        this.history = new ArrayList<>();
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        if (continentName != null && !continentName.isBlank()) {
            this.continentName = continentName;
        }
    }

    public List<TerritorialChange> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public void addChange(TerritorialChange change) {
        if (change != null) {
            history.add(change);
        }
    }

    /**
     * внутренний класс для хранения информации об истории изменения
     * территориального деления Европы на государства.
     */
    public class TerritorialChange {
        private final int year;
        private final String eventDescription;
        private final String stateName;
        private final double areaSqKm;
        private final String status;

        public TerritorialChange(int year, String eventDescription, String stateName, double areaSqKm, String status) {
            if (year < 0 || year > 2100) {
                throw new IllegalArgumentException("Некорректный год: " + year);
            }
            if (areaSqKm <= 0) {
                throw new IllegalArgumentException("Площадь должна быть положительной: " + areaSqKm);
            }
            if (stateName == null || stateName.isBlank()) {
                throw new IllegalArgumentException("Название государства не может быть пустым");
            }
            this.year = year;
            this.eventDescription = (eventDescription == null || eventDescription.isBlank()) ? "Историческое событие" : eventDescription;
            this.stateName = stateName;
            this.areaSqKm = areaSqKm;
            this.status = (status == null || status.isBlank()) ? "Изменение статуса" : status;
        }

        public int getYear() {
            return year;
        }

        public String getEventDescription() {
            return eventDescription;
        }

        public String getStateName() {
            return stateName;
        }

        public double getAreaSqKm() {
            return areaSqKm;
        }

        public String getStatus() {
            return status;
        }

        public String getContinentContext() {
            return Europe.this.continentName;
        }

        @Override
        public String toString() {
            return String.format("[%s | %d г.] %s — %s (Площадь: %.1f км², Статус: %s)",
                    continentName, year, stateName, eventDescription, areaSqKm, status);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TerritorialChange that)) return false;
            return year == that.year &&
                    Double.compare(that.areaSqKm, areaSqKm) == 0 &&
                    Objects.equals(eventDescription, that.eventDescription) &&
                    Objects.equals(stateName, that.stateName) &&
                    Objects.equals(status, that.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(year, eventDescription, stateName, areaSqKm, status);
        }
    }
}
