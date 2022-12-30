package com.partola.PracticeSpringBootApp.dao;

import com.partola.PracticeSpringBootApp.entities.*;
import com.partola.PracticeSpringBootApp.forms.FindFoodForm;
import com.partola.PracticeSpringBootApp.services.FoodService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Ivan Partola
 * @date 07.12.2022
 */
public class MySQLDAO implements DAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "food";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "root";

    private static final String SHOW_PORK = "SELECT * FROM food.pork";
    private static final String SHOW_BEEF = "SELECT * FROM food.beef";
    private static final String SHOW_APPLE = "SELECT * FROM food.apple";
    private static final String SHOW_ONION = "SELECT * FROM food.onion";
    private static final String ADD = "INSERT INTO tableName (satiety, weight, daysAge) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM tableName WHERE daysAge > ?";
    private static final String FIND = "SELECT * FROM tableName WHERE variableName = ?";

    @Override
    public List<Food> show() {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_LOGIN, DB_PASS)) {
            for (FoodType foodType : FoodType.values()) {
                ResultSet resultSet = connection.createStatement().executeQuery(getQueryByFoodType(foodType));
                resultSetToList(foods, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foods;
    }

    private void resultSetToList(List<Food> foods, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Food food = FoodService.getFoodBySatiety(resultSet.getInt("satiety"));
            food.setSatiety(resultSet.getInt("satiety"));
            food.setWeight(resultSet.getDouble("weight"));
            food.setDaysAge(resultSet.getDouble("daysAge"));

            foods.add(food);
        }

        resultSet.close();
    }

    @Override
    public void addFood(Food food) {
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_LOGIN, DB_PASS)) {

            String rightAction = ADD.replaceAll("tableName", food.getClass().getSimpleName().toLowerCase(Locale.ROOT));

            PreparedStatement preparedStatement = connection.prepareStatement(rightAction);

            preparedStatement.setInt(1, food.getSatiety());
            preparedStatement.setDouble(2, food.getWeight());
            preparedStatement.setDouble(3, food.getDaysAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSpoiledFood(FoodType foodType) {

        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_LOGIN, DB_PASS)) {

            String rightAction = DELETE.replaceAll("tableName", foodType.getDisplayName().toLowerCase(Locale.ROOT));

            PreparedStatement preparedStatement = connection.prepareStatement(rightAction);

            preparedStatement.setDouble(1, FoodService.getExpirationDays(foodType));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> findFoodWhereVariableIs(FoodType foodType, Variables variable, Double value) {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_LOGIN, DB_PASS)) {

            String rightAction = FIND.replaceAll("tableName", foodType.getDisplayName().toLowerCase(Locale.ROOT))
                    .replaceAll("variableName", variable.getVariableName());

            PreparedStatement preparedStatement = connection.prepareStatement(rightAction);
            preparedStatement.setDouble(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSetToList(foods, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return foods;
    }
    
    private String getQueryByFoodType (FoodType foodType) {
        String resultQuery = null;
        switch (foodType) {
            case PORK ->  resultQuery = SHOW_PORK;
            case BEEF ->  resultQuery = SHOW_BEEF;
            case APPLE ->  resultQuery = SHOW_APPLE;
            case ONION ->  resultQuery = SHOW_ONION;
        }
        
        return resultQuery;
    }
}
