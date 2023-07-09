package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
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

        mealsToCheck.clear();
        Assertions.assertThrows(NoFoodFoundException.class, () ->
                menuService.findFoodCheaperThanPrice(mealsToCheck, nameOfFoodWithPriceLimit));
    }



    @Test
    void findFoodContaining() {
        // given
        Produce tomato = createProduct("tomato", ProductType.VEGETABLE);
        Produce cucumber = createProduct("cucumber", ProductType.VEGETABLE);

        Produce beef = createProduct("beef", ProductType.MEAT);
        Produce chicken = createProduct("chicken", ProductType.MEAT);

        List<Produce> vegeProducts = new ArrayList<>();
        vegeProducts.add(tomato);
        vegeProducts.add(cucumber);

        List<Produce> meatProducts = new ArrayList<>();
        meatProducts.add(beef);
        meatProducts.add(chicken);

        Meal vegeMeal = createFullMeal("Salad", DietType.VEGETARIAN, vegeProducts, 100, 20);
        Meal meatMeal = createFullMeal("Hamburger", DietType.REGULAR, meatProducts, 400, 30);

        // when
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(vegeMeal);
        mealsToCheck.add(meatMeal);

        // then
        Produce productToVerify = createProduct("tomato", ProductType.VEGETABLE);
        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultMeal = menuService.findFoodContaining(mealsToCheck, productToVerify);

        Assertions.assertEquals(1, resultMeal.size());
        Assertions.assertEquals("Salad", resultMeal.get(0).getName());


    }

    @Test
    void findFoodExcludingAll() {
        // given
        Produce tomato = createProduct("tomato", ProductType.VEGETABLE);
        Produce cucumber = createProduct("cucumber", ProductType.VEGETABLE);

        Produce pineapple = createProduct("pineapple", ProductType.FRUIT);

        Produce beef = createProduct("beef", ProductType.MEAT);
        Produce chicken = createProduct("chicken", ProductType.MEAT);

        List<Produce> toSalad = new ArrayList<>();
        toSalad.add(tomato);
        toSalad.add(pineapple);
        toSalad.add(cucumber);

        List<Produce> toPizza = new ArrayList<>();
        toPizza.add(chicken);
        toPizza.add(pineapple);
        toPizza.add(cucumber);

        List<Produce> toMeatFood = new ArrayList<>();
        toMeatFood.add(chicken);
        toMeatFood.add(beef);
        toMeatFood.add(tomato);

        Meal salad = createFullMeal("Salad", DietType.VEGETARIAN, toSalad, 100, 10);
        Meal pizza = createFullMeal("Pizza", DietType.REGULAR, toPizza, 200, 15);
        Meal meatFood = createFullMeal("Meat Food", DietType.REGULAR, toMeatFood, 400, 25);

        // then
        List<Meal> mealsToCheck = new ArrayList<>();
        mealsToCheck.add(salad);
        mealsToCheck.add(pizza);
        mealsToCheck.add(meatFood);

        List<Produce> productToCheck = new ArrayList<>();
        productToCheck.add(pineapple);
        productToCheck.add(cucumber);

        MenuServiceImpl menuService = new MenuServiceImpl();
        List<Meal> resultMeals = menuService.findFoodExcludingAll(mealsToCheck, productToCheck);

        Assertions.assertEquals(1, resultMeals.size());
        Assertions.assertEquals("Meat Food", resultMeals.get(0).getName());
        Assertions.assertEquals(400, resultMeals.get(0).getCalories());
    }

    private Meal createVegetrianMeal() {

        Meal vegetarianMeal = new Meal();
        vegetarianMeal.setName("Cool vege meal");
        vegetarianMeal.setDietType(DietType.VEGETARIAN);
        vegetarianMeal.setCalories(150);
        vegetarianMeal.setPrice(20);

        return vegetarianMeal;
    }

//
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

//    private Meal createSalad() {
//
//        Meal meal = new Meal();
//        meal.setName("Salad");
//        meal.setCalories(250);
//        meal.setDietType(DietType.VEGETARIAN);
//        meal.setPrice(25);
//
//        List<Produce> products = new ArrayList<>();
//        products.add(createVegetable("Lettuce"));
//        products.add(createVegetable("Tomato"));
//        products.add(createVegetable("Cucumber"));
//        meal.setProducts(products);
//        return meal;
//    }

    private Produce createProduct(String name, ProductType productType){
        Produce product = new Produce();
        product.setName(name);
        product.setProductType(productType);

        return product;
    }

    private Meal createFullMeal(String name, DietType dietType, List<Produce> products, int calories, int price) {

        Meal meal = new Meal();
        meal.setName(name);
        meal.setDietType(dietType);
        meal.setProducts(products);
        meal.setCalories(calories);
        meal.setPrice(price);

        return meal;

    }

    }