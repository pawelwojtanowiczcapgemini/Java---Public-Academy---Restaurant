package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO IMPLEMENT
public class MenuServiceImpl implements MenuService {
    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        if (meals == null) {
            throw new IllegalArgumentException("input == null!");
        }

        List<Meal> vegetarianFood = meals
                .stream()
                .filter(mealItem -> mealItem.getDietType() == DietType.VEGETARIAN)
                .collect(Collectors.toList());

        if (vegetarianFood.isEmpty()) {
            NoFoodFoundException noFoodFoundException = new NoFoodFoundException();
            throw noFoodFoundException;
        }

        return vegetarianFood;
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        List<Meal> typedFood;

            typedFood = meals
                .stream()
                .filter(mealItem -> mealItem.getDietType() == diet)
                .collect(Collectors.toList());

        return typedFood;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {
        List<Meal> cheapFood = meals
                .stream()
                .filter(mealItem -> mealItem.getPrice() < price)
                .collect(Collectors.toList());

        return cheapFood;
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        List<Meal> caloriesMeal = meals
                .stream()
                .filter(mealItem -> mealItem.getCalories() < maxCalories & mealItem.getCalories() > minCalories)
                .collect(Collectors.toList());

        return caloriesMeal;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, String name) {
        List<Meal> limitedPriceFood = meals
                .stream()
                .filter(mealItem -> mealItem.getName() == name)
                .collect(Collectors.toList());

        NoFoodFoundException noFoodFoundException = new NoFoodFoundException();
        if (limitedPriceFood.size() ==0)
            throw noFoodFoundException;

        int priceLimit = limitedPriceFood.get(0).getPrice();

        List<Meal> cheapFood = meals
                .stream()
                .filter(mealItem -> mealItem.getPrice() < priceLimit)
                .collect(Collectors.toList());

        return cheapFood;
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        return null;
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        return null;
    }
}