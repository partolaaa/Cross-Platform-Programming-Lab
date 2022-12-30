package com.partola.PracticeSpringBootApp.forms;

import com.partola.PracticeSpringBootApp.entities.FoodType;

/**
 * @author Ivan Partola
 * @date 17.12.2022
 */
public class DeleteSpoiledFoodForm {
    private FoodType foodType;

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
