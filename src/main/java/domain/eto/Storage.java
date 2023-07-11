package domain.eto;

import domain.ProductType;

import java.util.AbstractMap;
import java.util.Map;

public class Storage {

    public Produce createProduct(String name, ProductType productType) {
        Produce product = new Produce();
        product.setName(name);
        product.setProductType(productType);

        return product;
    }
    public Map<Produce, Integer> createProductStorage() {
        Map<Produce, Integer> productStorage = Map.ofEntries(
                new AbstractMap.SimpleEntry<>(createProduct("milk", ProductType.DAIRY), 1),
                new AbstractMap.SimpleEntry<>(createProduct("chicken", ProductType.MEAT), 1),
                new AbstractMap.SimpleEntry<>(createProduct("flour", ProductType.GRAIN), 1),
                new AbstractMap.SimpleEntry<>(createProduct("pineapple", ProductType.FRUIT), 0),
                new AbstractMap.SimpleEntry<>(createProduct("tomato", ProductType.VEGETABLE), 1),
                new AbstractMap.SimpleEntry<>(createProduct("jam", ProductType.PLANT_BASED), 1),
                new AbstractMap.SimpleEntry<>(createProduct("salmon", ProductType.FISH), 1),
                new AbstractMap.SimpleEntry<>(createProduct("egg", ProductType.EGGS), 1),
                new AbstractMap.SimpleEntry<>(createProduct("pepper", ProductType.SEASONING), 1)
                );

        return productStorage;
    }

//    public Map<ProductType, Integer> createProductMap() {
//        Map<ProductType, Integer> productMap = new HashMap<>();
//
//        productMap.put(ProductType.DAIRY, 11);
//        productMap.put(ProductType.MEAT, 15);
//        productMap.put(ProductType.GRAIN, 12);
//        productMap.put(ProductType.FRUIT, 30);
//        productMap.put(ProductType.VEGETABLE, 12);
//        productMap.put(ProductType.PLANT_BASED, 14);
//        productMap.put(ProductType.FISH, 8);
//        productMap.put(ProductType.EGGS, 5);
//        productMap.put(ProductType.SEASONING, 13);
//
//        return productMap;
//    }
}