package org.example.motorcyclist.factory;

/**
 * выдает нужный creator по строке с типом.
 */
public class AmmunitionCreatorProvider {
    private static final String HELMET = "helmet";
    private static final String JACKET = "jacket";
    private static final String GLOVES = "gloves";
    private static final String BOOTS = "boots";

    public static AmmunitionCreator getCreator(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип амуниции не задан");
        }

        return switch (type.trim().toLowerCase()) {
            case HELMET -> new HelmetCreator();
            case JACKET -> new JacketCreator();
            case GLOVES -> new GlovesCreator();
            case BOOTS -> new BootsCreator();
            default -> throw new IllegalArgumentException("Неизвестный тип амуниции: " + type);
        };
    }
}