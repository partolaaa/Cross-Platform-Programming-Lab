package com.partola.PracticeSpringBootApp.dao;

import com.partola.PracticeSpringBootApp.entities.Food;
import com.partola.PracticeSpringBootApp.entities.FoodType;
import com.partola.PracticeSpringBootApp.entities.Variables;
import com.partola.PracticeSpringBootApp.forms.FindFoodForm;

import java.util.List;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public interface DAO {
    List<Food> show ();
    void addFood (Food food);

    void deleteSpoiledFood(FoodType foodType);

    List<Food> findFoodWhereVariableIs(FoodType foodType, Variables variable, Double value);
}
