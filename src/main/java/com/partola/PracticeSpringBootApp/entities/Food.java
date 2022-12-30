package com.partola.PracticeSpringBootApp.entities;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public abstract class Food implements Comparable<Food>{
    private int satiety; // from 0 to 5
    private double weight;
    private double daysAge;

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDaysAge(double daysAge) {
        this.daysAge = daysAge;
    }

    public double getWeight() {
        return weight;
    }

    public double getDaysAge() {
        return daysAge;
    }

    public Food(int satiety, double weight, double daysAge) {
        this.satiety = satiety;
        this.weight = weight;
        this.daysAge = daysAge;
    }

    public Food() {}

    @Override
    public String toString(){
        return "Satiety of " + getClass().getSimpleName() + " is " + satiety;
    }

    public int getSatiety() {
        return satiety;
    }
}
