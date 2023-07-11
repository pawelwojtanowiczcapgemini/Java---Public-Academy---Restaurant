package service.api;

import domain.eto.Meal;
import domain.eto.Produce;

import java.util.Map;

public interface StorageService {
    public void canMealBePreparedFromProductsInStorage(Meal meal, Map<Produce, Integer> productStorage);
}