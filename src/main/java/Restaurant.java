import domain.ProductType;
import domain.eto.CommonStorage;
import domain.eto.Produce;

public class Restaurant {

    public static void main(String[] args) {

        CommonStorage commonStorage = new CommonStorage();

        commonStorage.createEmptyProductStorage();
        Produce product = new Produce();
        product.setName("tomato");
        product.setProductType(ProductType.VEGETABLE);

        Produce product2 = new Produce();
        product2.setName("milk");
        product2.setProductType(ProductType.DAIRY);

        System.out.println(product.getName());
        System.out.println(commonStorage.getProductStorage());

        commonStorage.addToStorage(product, 1);

        System.out.println(commonStorage.getProductStorage());

        commonStorage.addToStorage(product2, 2);
        System.out.println(commonStorage.getProductStorage());

        commonStorage.checkInStorage(product);
        System.out.println(commonStorage.getProductStorage());
        System.out.println(commonStorage.checkInStorage(product));


        commonStorage.removeFromStorage(product, 1);
        System.out.println(commonStorage.getProductStorage());

        commonStorage.clearStorage();
        System.out.println(commonStorage.getProductStorage());

//    Storage storage = new Storage();
//    System.out.println(storage.createProductStorage());

    }
}