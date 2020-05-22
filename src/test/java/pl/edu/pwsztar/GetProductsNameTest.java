package pl.edu.pwsztar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GetProductsNameTest {

  private ShoppingCart shoppingCart;

  @BeforeEach
  void setup() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void getNamesTest() {
    //given there are apples, bananas , strawberries and kiwis in the basket
    shoppingCart.addProducts("Apple", 10, 2);
    shoppingCart.addProducts("Banana", 5, 3);
    shoppingCart.addProducts("Strawberry", 20, 1);
    shoppingCart.addProducts("Kiwi", 1, 10);
    //when gets products names
    List<String> result = shoppingCart.getProductsNames();
    //then gets all products names
    assertTrue(new ArrayList<>(Arrays.asList("Apple", "Banana", "Kiwi", "Strawberry")).containsAll(result));
  }

  @Test
  void canNotGetTest() {
    //when gets products names
    List<String> result = shoppingCart.getProductsNames();
    //then gets empty list
    assertTrue(result.isEmpty());
  }

}
