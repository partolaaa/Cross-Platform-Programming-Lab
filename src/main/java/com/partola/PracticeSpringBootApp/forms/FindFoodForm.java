package com.partola.PracticeSpringBootApp.forms;

import com.partola.PracticeSpringBootApp.entities.Food;
import com.partola.PracticeSpringBootApp.entities.FoodType;
import com.partola.PracticeSpringBootApp.entities.Variables;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public class FindFoodForm {
    FoodType foodType;
    Variables variable;
    Double value;

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Variables getVariable() {
        return variable;
    }

    public void setVariable(Variables variable) {
        this.variable = variable;
    }
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
