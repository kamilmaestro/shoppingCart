package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AddProductTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void addTest() {
    //when add two apples to basket
    Product product = new Product("Apple", 10, 2);
    boolean result = shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //then basket contains this product
    int appleNb = shoppingCart.getQuantityOfProduct("Apple");
    assertTrue(result);
    assertEquals(2, appleNb);
  }

  @Test
  void changeWhenAddTest() {
    //given there is apple in the basket
    shoppingCart.addProducts("Apple", 10, 1);
    //when add another two apples to the basket
    Product product = new Product("Apple", 10, 2);
    boolean result = shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //then basket contains 3 apples
    int appleNb = shoppingCart.getQuantityOfProduct("Apple");
    assertTrue(result);
    assertEquals(3, appleNb);
  }

  @Test
  void notAddTest() {
    //given there is apple in the basket
    shoppingCart.addProducts("Apple", 10, 1);
    //when add another two apples with changed price to the basket
    Product product = new Product("Apple", 15, 2);
    boolean result = shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //then apples are not added
    assertFalse(result);
  }

  @ParameterizedTest
  @CsvSource({
      "     ,  1,  2",
      "Apple,  4, -3",
      "Apple, -1, 12",
  })
  void notProperDataTest(String productName, int price, int quantity) {
    //when adds product with wrong data
    Product product = new Product(productName, price, quantity);
    boolean result = shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //then apples are not added
    assertFalse(result);
  }

  @Test
  void maxLimitTest() {
    //given there are 495 apples in the basket
    shoppingCart.addProducts("Apple", 10, 495);
    //when adds 6 bananas with changed price to the basket
    Product product = new Product("Banana", 15, 6);
    boolean result = shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //then apples are not added
    assertFalse(result);
  }

}
