package pl.edu.pwsztar;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

class Product {

  private final String name;
  private final int price;
  private final int quantity;


  Product(String name, int price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  boolean hasNotProperData() {
    return !hasProperName() || price <= 0 || quantity <= 0;
  }

  private Boolean hasProperName() {
    return Optional.ofNullable(name)
        .map(StringUtils::isNotBlank)
        .orElse(false);
  }
}
