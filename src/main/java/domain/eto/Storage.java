package domain.eto;

import domain.ProductType;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    public Map<ProductType, Integer> createProductMap() {
        Map<ProductType, Integer> productMap = Map.ofEntries(
                new AbstractMap.SimpleEntry<>(ProductType.DAIRY, 1),
                new AbstractMap.SimpleEntry<>(ProductType.MEAT, 1),
                new AbstractMap.SimpleEntry<>(ProductType.GRAIN, 1),
                new AbstractMap.SimpleEntry<>(ProductType.FRUIT, 1),
                new AbstractMap.SimpleEntry<>(ProductType.VEGETABLE, 1),
                new AbstractMap.SimpleEntry<>(ProductType.PLANT_BASED, 1),
                new AbstractMap.SimpleEntry<>(ProductType.FISH, 1),
                new AbstractMap.SimpleEntry<>(ProductType.EGGS, 1),
                new AbstractMap.SimpleEntry<>(ProductType.SEASONING, 1)
        );
        return productMap;
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