package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DeleteProductTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void deleteTest() {
    //given there are four apples in the basket
    Product product = new Product("Apple", 10, 4);
    shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //when deletes two apples
    boolean result = shoppingCart.deleteProducts(product.getName(), 2);
    //then basket contains two apples
    int appleNb = shoppingCart.getQuantityOfProduct("Apple");
    assertTrue(result);
    assertEquals(2, appleNb);
  }

  @Test
  void deletePermanentlyTest() {
    //given there are four apples in the basket
    Product product = new Product("Apple", 10, 4);
    shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //when deletes four apples
    boolean result = shoppingCart.deleteProducts(product.getName(), 4);
    //then basket does not contain any apple
    int appleNb = shoppingCart.getQuantityOfProduct("Apple");
    assertTrue(result);
    assertEquals(0, appleNb);
  }

  @Test
  void canNotDeleteTest() {
    //given there are four apples in the basket
    Product product = new Product("Apple", 10, 4);
    shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //when deletes five apples
    boolean result = shoppingCart.deleteProducts(product.getName(), 5);
    //then apples are not deleted
    int appleNb = shoppingCart.getQuantityOfProduct("Apple");
    assertFalse(result);
    assertEquals(4, appleNb);
  }

  @ParameterizedTest
  @CsvSource({
      "     ,  1",
      "Apple, -3",
      "     , -1",
  })
  void triesDelete(String productName, int quantity) {
    //when tries to delete with wrong data
    boolean result = shoppingCart.deleteProducts(productName, quantity);
    //then products are not deleted
    assertFalse(result);
  }

}
