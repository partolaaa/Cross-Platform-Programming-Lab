package com.partola.PracticeSpringBootApp.dao;

import com.partola.PracticeSpringBootApp.entities.*;
import com.partola.PracticeSpringBootApp.forms.FindFoodForm;
import com.partola.PracticeSpringBootApp.services.FoodService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public class ListDAO implements DAO {

    private static List<Food> foods = new ArrayList<Food>();

    static
    {
        foods.add(new Pork(5, 0.9, 3));
        foods.add(new Beef(4, 1.2, 5));
        foods.add(new Onion(1, 0.2, 6));
        foods.add(new Apple(2, 0.15, 2));

        //spoiled food for example
        foods.add(new Pork(5, 0.9, 50));
        foods.add(new Beef(4, 1.2, 50));
        foods.add(new Onion(1, 0.2, 50));
        foods.add(new Apple(2, 0.15, 50));
    }

    @Override
    public List<Food> show() {
        return foods;
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void deleteSpoiledFood(FoodType foodType) {
        foods = foods.stream()
                .filter(current -> {
                    //если это объекты одного класса
                    boolean isGood = true;
                    if (foodType.getDisplayName().equals(current.getClass().getSimpleName())) {
                        isGood = current.getDaysAge() < FoodService.getExpirationDays(foodType);
                    }
                    return isGood;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> findFoodWhereVariableIs(FoodType foodType, Variables variable, Double value) {
        List<Food> searchResults = new ArrayList<>();

        for (Food food : foods) {
            if (food.getClass().getSimpleName().equals(foodType.getDisplayName()))
                if (findVariableValue(food, variable, value)) {
                    searchResults.add(food);
                }
        }
        return searchResults;
    }

    private boolean findVariableValue (Food food ,Variables variable, Double value) {
        boolean isCorrect = false;
        switch (variable) {
            case SATIETY -> isCorrect = food.getSatiety() == value;
            case WEIGHT -> isCorrect = food.getWeight() == value;
            case DAYS_AGE -> isCorrect = food.getDaysAge() == value;
        }

        return isCorrect;
    }
}
