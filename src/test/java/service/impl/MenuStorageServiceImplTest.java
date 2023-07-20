package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.CommonStorage;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.exception.NoProductFoundInStorageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuStorageServiceImplTest {

    //this is only to check if the test is passed in case of super Override -- I'm not doing another ones in this range
    @Test
    void shouldFindVegetarianFood() {
        // given: vegetarian meal needed, regular meal needed

        Meal vegetarianMeal = createVegetrianMeal();
        Meal regularMeal = createRegularMeal();

        // when: I call the method findVegetarianFood

        List<Meal> mealsToCheck = new ArrayList<Meal>();
        mealsToCheck.add(vegetarianMeal);
        mealsToCheck.add(regularMeal);

        MenuStorageServiceImpl menuService = new MenuStorageServiceImpl();

        List<Meal> results = menuService.findVegetarianFood(mealsToCheck);

        // then: return list has only one element & this element has DietType == VEGETARIAN

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Cool vege meal", results.get(0).getName());
        Assertions.assertEquals(DietType.VEGETARIAN, results.get(0).getDietType());
    }

    private Meal createVegetrianMeal() {

        Meal vegetarianMeal = new Meal();
        vegetarianMeal.setName("Cool vege meal");
        vegetarianMeal.setDietType(DietType.VEGETARIAN);
        vegetarianMeal.setCalories(150);
        vegetarianMeal.setPrice(20);

        return vegetarianMeal;
    }

    private Meal createRegularMeal() {
        Meal regularMeal = new Meal();
        regularMeal.setName("Not coll because it's meat meal");
        regularMeal.setDietType(DietType.REGULAR);

        return regularMeal;
    }

// this is the test mentioned in task 4

//    @Test
//    public void shouldCanMealBePreparedFromProductsInStorage() {
//        Meal salad = createSalad();
//
//        CommonStorage commonStorage = new CommonStorage();
//
//
//        Map<Produce, Integer> productStorage = storage.createProductStorage();
//
//        StorageServiceImpl storageService = new StorageServiceImpl();
//
//        Assertions.assertThrows(NoProductFoundInStorageException.class, () ->
//                storageService.canMealBePreparedFromProductsInStorage(salad, productStorage));
//    }
//
//    public Produce createProduct(String name, ProductType productType) {
//        Produce product = new Produce();
//        product.setName(name);
//        product.setProductType(productType);
//
//        return product;
//    }
//
//    public List<Produce> createSaladProductList() {
//
//        List<Produce> saladproductList = new ArrayList<>();
//        saladproductList.add(createProduct("tomato", ProductType.VEGETABLE));
//        saladproductList.add(createProduct("pepper", ProductType.SEASONING));
//        saladproductList.add(createProduct("pineapple", ProductType.FRUIT));
//
//        return saladproductList;
//    }
//
//    public Meal createSalad() {
//        Meal salad = new Meal();
//        salad.setName("Salad");
//        salad.setDietType(DietType.VEGETARIAN);
//        salad.setPrice(10);
//        salad.setCalories(150);
//        salad.setProducts(createSaladProductList());
//
//        return salad;
//    }


}