package com.partola.PracticeSpringBootApp.entities;

import java.util.Comparator;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public class GreenFood extends Food implements Comparator<Food> {
    GreenFood(int satiety, double weight, double daysAge) {
        super(satiety, weight, daysAge);
    }
    public GreenFood () {}
    @Override
    public int compareTo(Food food) {
        return Integer.compare(getSatiety(), food.getSatiety());
    }

    @Override
    public int compare(Food o1, Food o2) {
        return Integer.compare(o1.getSatiety(), o2.getSatiety());
    }
}
