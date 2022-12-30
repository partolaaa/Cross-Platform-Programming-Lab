package com.partola.PracticeSpringBootApp.services;

import com.partola.PracticeSpringBootApp.entities.*;
import com.partola.PracticeSpringBootApp.forms.AddFoodForm;

/**
 * @author Ivan Partola
 * @date 17.12.2022
 */
public class FoodService {
    public static int getExpirationDays (FoodType foodType) {
        int expirationDays = 0;

        switch (foodType) {
            case BEEF -> expirationDays = 5;
            case PORK -> expirationDays = 4;
            case ONION -> expirationDays = 15;
            case APPLE -> expirationDays = 10;
        }
        return expirationDays;
    }

    public static Food getFoodBySatiety (int satiety) {
        Food food = null;
        switch (satiety) {
            case 5 -> food = new Pork();
            case 4 -> food = new Beef();
            case 2 -> food = new Apple();
            case 1 -> food = new Onion();
        }
        
        return food;
    }

    public static Food fillFoodFromForm (Food food, AddFoodForm addFoodForm) {
        food.setSatiety(addFoodForm.getSatiety());
        food.setWeight(addFoodForm.getWeight());
        food.setDaysAge(addFoodForm.getDaysAge());

        return food;
    }
}
