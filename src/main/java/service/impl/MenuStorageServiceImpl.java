package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.exception.NoProductFoundInStorageException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuStorageServiceImpl extends MenuServiceImpl {
    private void canMealBePreparedFromProductsInCommonStorage(Meal meal, Map<Produce, Integer> productStorage) {


        List<Produce> productList = meal.getProducts()
                .stream()
                .toList();

        Map<Produce, Integer> filteredMap = productStorage.entrySet()
                .stream()
                .filter(entry -> productList.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Integer> valuesList = filteredMap.values()
                .stream()
                .toList();

        boolean isTrue = false;

        for (int i = 0; i < valuesList.size(); i++) {
            if (valuesList.get(i) > 0) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }

        if (!isTrue) {
            NoProductFoundInStorageException noProductFoundInStorageException = new NoProductFoundInStorageException();
            throw noProductFoundInStorageException;
        }

    }

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        return super.findVegetarianFood(meals);
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        return super.findFoodByType(meals, diet);
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {
        return super.findFoodCheaperThanPrice(meals, price);
    }

    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        return super.findFoodCheaperWithCalories(meals, minCalories, maxCalories);
    }

    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, String name) {
        return super.findFoodCheaperThanPrice(meals, name);
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        return super.findFoodContaining(meals, product);
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        return super.findFoodExcludingAll(meals, products);
    }
}