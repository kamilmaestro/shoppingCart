package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetProductPriceTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void getTest() {
    //given there are two apples
    shoppingCart.addProducts("Apple", 10, 2);
    //when gets quantity
    int result = shoppingCart.getProductPrice("Apple");
    //then basket contains products with value 45
    assertEquals(10, result);
  }

  @Test
  void canNotGetTest() {
    //when gets quantity
    int result = shoppingCart.getProductPrice("");
    //then basket contains products with value 45
    assertEquals(0, result);
  }

}
