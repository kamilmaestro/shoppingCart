package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetSumProductsPricesTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void GetSumPricesTest() {
    //given there are two apples, three bananas and 10 kiwis in the basket
    shoppingCart.addProducts("Apple", 10, 2);
    shoppingCart.addProducts("Banana", 5, 3);
    shoppingCart.addProducts("Kiwi", 1, 10);
    //when gets quantity
    int result = shoppingCart.getSumProductsPrices();
    //then basket contains products with value 45
    assertEquals(45, result);
  }

  @Test
  void canNotGetTest() {
    //when gets quantity
    int result = shoppingCart.getSumProductsPrices();
    //then basket is empty
    assertEquals(0, result);
  }

}
