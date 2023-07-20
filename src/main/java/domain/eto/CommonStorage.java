package domain.eto;

import java.util.*;

public class CommonStorage {

    static Map<Produce, Integer> productStorage;

    public CommonStorage() {
    }

    public static Map<Produce, Integer> getProductStorage() {
        return productStorage;
    }

    public static void setProductStorage(Map<Produce, Integer> productStorage) {
        CommonStorage.productStorage = productStorage;
    }

    public static void createEmptyProductStorage() {
        productStorage = new HashMap<>();
    }

    public static void addToStorage(Produce product, Integer quantity) {
        productStorage.put(product, quantity);
    }

    public static void removeFromStorage(Produce product, Integer quantity) {
        productStorage.remove(product, quantity);
    }

    public static void clearStorage() {
        productStorage.clear();
    }

    public boolean checkInStorage(Produce product) {
        boolean isIn = productStorage.containsKey(product);
        return isIn;
    }

}