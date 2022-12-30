package com.partola.PracticeSpringBootApp.forms;

import com.partola.PracticeSpringBootApp.entities.FoodType;

/**
 * @author Ivan Partola
 * @date 17.12.2022
 */
public class AddFoodForm {
    FoodType foodType;
    private int satiety; // from 0 to 5
    private double weight;
    private double daysAge;

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDaysAge() {
        return daysAge;
    }

    public void setDaysAge(double daysAge) {
        this.daysAge = daysAge;
    }
}
