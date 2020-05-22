package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetQuantityOfProductTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void getQuantityTest() {
    //given there are two apples in the basket
    Product product = new Product("Apple", 10, 2);
    shoppingCart.addProducts(product.getName(), product.getPrice(), product.getQuantity());
    //when gets quantity
    int result = shoppingCart.getQuantityOfProduct(product.getName());
    //then basket contains two apples
    assertEquals(2, result);
  }

  @Test
  void canNotGetTest() {
    //when gets quantity of non existing product
    int result = shoppingCart.getQuantityOfProduct("");
    //then basket is empty
    assertEquals(0, result);
  }
}
