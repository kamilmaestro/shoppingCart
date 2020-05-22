package pl.edu.pwsztar;

import java.util.*;

class Basket {

  private final Map<String, Product> products = new HashMap<String, Product>();

  Product save(Product toSave) {
    products.put(toSave.getName(), toSave);
    return toSave;
  }

  Optional<Product> findByName(String productName) {
    return Optional.ofNullable(products.get(productName));
  }

  int getAllProductsQuantity() {
    return products.values().stream()
        .mapToInt(Product::getQuantity)
        .sum();
  }

  void deleteByName(String productName) {
    products.remove(productName);
  }

  int getAllProductsPricesSum() {
    return products.values().stream()
        .mapToInt(product -> product.getPrice() * product.getQuantity())
        .sum();
  }

  List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

}
