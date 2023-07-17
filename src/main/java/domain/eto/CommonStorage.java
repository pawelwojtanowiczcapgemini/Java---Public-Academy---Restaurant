package domain.eto;

import domain.ProductType;

import java.util.*;

public class CommonStorage {

    static Map<Produce, Integer> productsStorage;

    public CommonStorage() {
    }

    public static Map<Produce, Integer> getProductsStorage() {
        return productsStorage;
    }

    public static void setProductsStorage(Map<Produce, Integer> productsStorage) {
        CommonStorage.productsStorage = productsStorage;
    }

    public static void createEmptyProductStorage() {
        productsStorage = new HashMap<>();
    }

    public static void addToStorage(Produce product, Integer quantity) {
        productsStorage.put(product, quantity);
    }

    public static void removeFromStorage(Produce product, Integer quantity) {
        productsStorage.remove(product, quantity);
    }

    public static void clearStorage() {
        productsStorage.clear();
    }

    public boolean checkInStorage(Produce product) {
        boolean isIn = productsStorage.containsKey(product);
        return isIn;
    }

}