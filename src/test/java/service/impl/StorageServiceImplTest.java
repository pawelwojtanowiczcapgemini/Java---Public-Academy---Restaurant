package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;
import service.exception.NoProductFoundInStorageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceImplTest {
    @Test
    public void shouldCanMealBePreparedFromProductsInStorage() {
        Meal salad = createSalad();

        Storage storage = new Storage();
        Map<Produce, Integer> productStorage = storage.createProductStorage();

        StorageServiceImpl storageService = new StorageServiceImpl();

        Assertions.assertThrows(NoProductFoundInStorageException.class, () ->
                storageService.canMealBePreparedFromProductsInStorage(salad, productStorage));
    }

    public Produce createProduct(String name, ProductType productType) {
        Produce product = new Produce();
        product.setName(name);
        product.setProductType(productType);

        return product;
    }
    public List<Produce> createSaladProductList() {

        List<Produce> saladproductList = new ArrayList<>();
        saladproductList.add(createProduct("tomato", ProductType.VEGETABLE));
        saladproductList.add(createProduct("pepper", ProductType.SEASONING));
        saladproductList.add(createProduct("pineapple", ProductType.FRUIT));

        return saladproductList;
    }

    public Meal createSalad() {
        Meal salad = new Meal();
        salad.setName("Salad");
        salad.setDietType(DietType.VEGETARIAN);
        salad.setPrice(10);
        salad.setCalories(150);
        salad.setProducts(createSaladProductList());

        return salad;
    }

}