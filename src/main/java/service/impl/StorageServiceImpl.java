package service.impl;

import domain.eto.Meal;
import domain.eto.Produce;
import service.api.StorageService;
import service.exception.NoProductFoundInStorageException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageServiceImpl implements StorageService {

    @Override
    public void canMealBePreparedFromProductsInStorage(Meal meal, Map<Produce, Integer> productStorage) {

        List<Produce> productList = meal.getProducts()
                .stream()
                .collect(Collectors.toList());

        Map<Produce, Integer> filteredMap = productStorage.entrySet()
                .stream()
                .filter(entry -> productList.contains(entry.getKey()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Integer> valuesList = filteredMap.values().stream().collect(Collectors.toList());

        boolean isTrue = false;

        for (int i = 0; i < valuesList.size(); i++) {
            if (valuesList.get(i) > 0) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }

        if(isTrue == false) {
            NoProductFoundInStorageException noProductFoundInStorageException = new NoProductFoundInStorageException();
            throw noProductFoundInStorageException;
        }

    }

}