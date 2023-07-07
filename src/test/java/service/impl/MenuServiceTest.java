package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

class MenuServiceTest {

    @Test
    void shouldFindVegetarianFood() {
        // given: vegetarian meal needed, regular meal needed

        Meal vegetarianMeal = createVegetrianMeal();
        Meal regularMeal = createRegularMeal();

        // when: I call the method findVegetarianFood

        List<Meal> mealsToCheck = new ArrayList<Meal>();
        mealsToCheck.add(vegetarianMeal);
        mealsToCheck.add(regularMeal);

        MenuServiceImpl menuService = new MenuServiceImpl();

        List<Meal> results = menuService.findVegetarianFood(mealsToCheck);

        // then: return list has only one element & this element has DietType == VEGETARIAN

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Cool vege meal", results.get(0).getName());
        Assertions.assertEquals(DietType.VEGETARIAN, results.get(0).getDietType());

    }

    @Test
    public void shouldThrowExceptionWhenInputDoesNotContainVegetarian() {
        // given
        Meal regularMeal = createRegularMeal();

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(regularMeal);


        MenuServiceImpl menuService = new MenuServiceImpl();

        // then
        Assertions.assertThrows(NoFoodFoundException.class, () ->
                menuService.findVegetarianFood(mealsToCheck));

    }

    @Test
    public void shouldProperlyHandleNullAsInput() {
        // given
        List<Meal> mealsToCheck = null;

        // when
        MenuServiceImpl menuService = new MenuServiceImpl();

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                menuService.findVegetarianFood(mealsToCheck));
    }

    @Test
    void findFoodByType() {
        // given
        Meal vegetarianMeal = createVegetrianMeal();
        Meal regularMeals = createRegularMeal();
        Meal secondRegularMeal = createSecondRegularMeal();

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(vegetarianMeal);
        mealsToCheck.add(regularMeals);
        mealsToCheck.add(secondRegularMeal);

        // then
        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultsRegular = menuService.findFoodByType(mealsToCheck, DietType.REGULAR);
        List<Meal> resultsVegetarian = menuService.findFoodByType(mealsToCheck, DietType.VEGETARIAN);

        Assertions.assertEquals(2, resultsRegular.size());
        Assertions.assertTrue(resultsRegular.get(0).getName().contains("meat"));
        Assertions.assertTrue(resultsRegular.get(1).getName().contains("meat"));

        Assertions.assertEquals(1, resultsVegetarian.size());
        Assertions.assertTrue(resultsVegetarian.get(0).getName().contains("vege"));


    }

    @Test
    void findFoodCheaperThanPrice() {
        // given
        Meal cheapMeal = createCheapMeal();
        Meal expensiveMeal = createExpensiveMeal();

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(cheapMeal);
        mealsToCheck.add(expensiveMeal);

        // then

        int priceLimit = 15;
        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultCheapMeal = menuService.findFoodCheaperThanPrice(mealsToCheck, priceLimit);

        Assertions.assertEquals(1, resultCheapMeal.size());
        Assertions.assertTrue(priceLimit > resultCheapMeal.get(0).getPrice());
    }

    @Test
    void findFoodCheaperWithCalories() {
        // given
        Meal highCaloriesMeal = createMealWithEnergyValue("Hamburger", 300);
        Meal lowCaloriesMeal = createMealWithEnergyValue("Salad", 71);

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(highCaloriesMeal);
        mealsToCheck.add(lowCaloriesMeal);

        //then
        int minCalories = 60;
        int maxCalories = 150;

        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultCaloriesMeal = menuService.findFoodCheaperWithCalories(mealsToCheck, minCalories, maxCalories);

        Assertions.assertEquals(1, resultCaloriesMeal.size());
        Assertions.assertEquals("Salad", resultCaloriesMeal.get(0).getName());
        Assertions.assertTrue(resultCaloriesMeal.get(0).getCalories() < maxCalories && resultCaloriesMeal.get(0).getCalories() > minCalories);
    }

    @Test
    void testFindFoodCheaperThanPrice() {
        // given
        Meal cheapMeal = createCheapMeal();
        Meal expensiveMeal = createExpensiveMeal();
        Meal middlePriceMeal = createMiddlePriceMeal();

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(cheapMeal);
        mealsToCheck.add(expensiveMeal);
        mealsToCheck.add(middlePriceMeal);

        // then

        String nameOfFoodWithPriceLimit = "Middle-price meal";
        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultCheapMeal = menuService.findFoodCheaperThanPrice(mealsToCheck, nameOfFoodWithPriceLimit);

        Assertions.assertEquals(1, resultCheapMeal.size());
        Assertions.assertEquals("Cheap meal",resultCheapMeal.get(0).getName());
    }



    @Test
    void findFoodContaining() {
    }

    @Test
    void findFoodExcludingAll() {
    }

    private Meal createVegetrianMeal() {

        Meal vegetarianMeal = new Meal();
        vegetarianMeal.setName("Cool vege meal");
        vegetarianMeal.setDietType(DietType.VEGETARIAN);

        return vegetarianMeal;
    }

    private Meal createRegularMeal() {
        Meal regularMeal = new Meal();
        regularMeal.setName("Not coll because it's meat meal");
        regularMeal.setDietType(DietType.REGULAR);

        return regularMeal;
    }

    private Meal createSecondRegularMeal() {
        Meal secondRegularMeal = new Meal();
        secondRegularMeal.setName("It's meat meal");
        secondRegularMeal.setDietType(DietType.REGULAR);

        return secondRegularMeal;
    }

    private Meal createCheapMeal() {
        Meal cheapMeal = new Meal();
        cheapMeal.setName("Cheap meal");
        cheapMeal.setPrice(10);

        return cheapMeal;
    }

    private Meal createExpensiveMeal() {
        Meal expensiveMeal = new Meal();
        expensiveMeal.setName("Expensive meal");
        expensiveMeal.setPrice(30);

        return expensiveMeal;
    }

    private Meal createMiddlePriceMeal() {
        Meal expensiveMeal = new Meal();
        expensiveMeal.setName("Middle-price meal");
        expensiveMeal.setPrice(20);

        return expensiveMeal;
    }

    private Meal createMealWithEnergyValue(String name, int calories) {
        Meal caloriesMeal = new Meal();
        caloriesMeal.setName(name);
        caloriesMeal.setCalories(calories);

        return caloriesMeal;
    }


}