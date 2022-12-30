package com.partola.PracticeSpringBootApp.entities;

/**
 * @author Ivan Partola
 * @date 17.12.2022
 */
public enum Variables {
    SATIETY("satiety"),
    WEIGHT("weight"),
    DAYS_AGE("daysAge");

    private final String variableName;

    Variables(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }
}
