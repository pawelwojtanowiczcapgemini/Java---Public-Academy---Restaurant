package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

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
        return null;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {
        return null;
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        return null;
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, String name) {
        return null;
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