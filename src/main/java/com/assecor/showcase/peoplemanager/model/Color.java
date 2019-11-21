package com.assecor.showcase.peoplemanager.model;

import java.util.logging.Logger;

/**
 * @author F_Behboudi@hotmail.com
 */
public enum Color {
    BLUE(1),
    GREEN(2),
    PURPLE(3),
    RED(4),
    YELLOW(5),
    TURQUOIS(6),
    WHITE(7);

    private static final Logger logger = Logger.getLogger(Color.class.getName());
    private int colorNumber;

    Color(int colorNumber) {
        this.colorNumber = colorNumber;
    }

    public static Color fromColorNumber(int colorNumber) {
        for (Color color : Color.values()) {
            if (color.colorNumber == colorNumber) {
                return color;
            }
        }
        String errorMessage = String.format("Color Number %d is not defined", colorNumber);
        logger.warning(errorMessage);
        throw new IllegalArgumentException(errorMessage);
    }

    public static int fromColorName(String colorName) {
        for (Color color : Color.values()) {
            if (color.name().equals(colorName)) {
                return color.colorNumber;
            }
        }
        String errorMessage = String.format("Color Number %s is not defined", colorName);
        logger.warning(errorMessage);
        throw new IllegalArgumentException(errorMessage);
    }
}
