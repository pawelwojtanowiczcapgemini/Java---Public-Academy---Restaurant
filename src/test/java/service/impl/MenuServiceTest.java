package service.impl;

import domain.DietType;
import domain.eto.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MenuServiceTest {

    @Test
    void shouldFindVegetarianFood() {

        // given: vegetarian meal needed, regular meal needed

        Meal vegetarianMeal = new Meal();
        vegetarianMeal.setName("Cool vege meal");
        vegetarianMeal.setDietType(DietType.VEGETARIAN);

        Meal regularMeal = new Meal();
        regularMeal.setName("Not coll because it's meat meal");
        regularMeal.setDietType(DietType.REGULAR);

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
    void findFoodByType() {
    }

    @Test
    void findFoodCheaperThanPrice() {
    }

    @Test
    void findFoodCheaperWithCalories() {
    }

    @Test
    void testFindFoodCheaperThanPrice() {
    }

    @Test
    void findFoodContaining() {
    }

    @Test
    void findFoodExcludingAll() {
    }
}