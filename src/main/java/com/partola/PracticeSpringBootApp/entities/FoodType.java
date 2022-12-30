package com.partola.PracticeSpringBootApp.entities;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public enum FoodType {
    PORK("Pork"),
    BEEF("Beef"),
    ONION("Onion"),
    APPLE("Apple");

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    FoodType(String displayName) {
        this.displayName = displayName;
    }
}
