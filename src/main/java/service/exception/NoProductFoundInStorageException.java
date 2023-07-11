package service.exception;

public class NoProductFoundInStorageException extends RuntimeException {

  @Override
  public String getMessage() {

    return "Product not found in the storage";
  }
}