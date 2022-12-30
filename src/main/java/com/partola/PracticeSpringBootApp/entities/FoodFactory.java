package com.partola.PracticeSpringBootApp.entities;

/**
 * @author Ivan Partola
 * @date 17.12.2022
 */
public class FoodFactory {
    private static Food food;

    public static Food getFoodInstance(FoodType foodType) {
        switch (foodType) {
            case APPLE -> food = new Apple();
            case PORK -> food = new Pork();
            case BEEF -> food = new Beef();
            case ONION -> food = new Onion();
        }
        return food;
    }
}
