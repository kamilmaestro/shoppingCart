package pl.edu.pwsztar;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShoppingCart implements ShoppingCartOperation {

    public static final int NO_PRODUCT = 0;
    private final Basket basket;

    public ShoppingCart() {
        this.basket = new Basket();
    }

    public boolean addProducts(String productName, int price, int quantity) {
        Product toSave = new Product(productName, price, quantity);
        if (canNotAdd(toSave)) {
            return false;
        }
        Optional<Product> product = basket.findByName(productName);
        return product
            .map(p -> savePreviouslyAddedProduct(p, toSave))
            .orElseGet(() -> saveProduct(toSave));
    }

    private boolean canNotAdd(Product toSave) {
        return toSave.hasNotProperData() || hasAchievedLimit(toSave.getQuantity());
    }

    private boolean hasAchievedLimit(int quantity) {
        return basket.getAllProductsQuantity() + quantity > PRODUCTS_LIMIT;
    }

    private boolean savePreviouslyAddedProduct(Product previouslyAdded, Product newProduct) {
        if (previouslyAdded.getPrice() == newProduct.getPrice()) {
            int quantity = previouslyAdded.getQuantity() + newProduct.getQuantity();
            Product toSave = new Product(previouslyAdded.getName(), previouslyAdded.getPrice(), quantity);
            basket.save(toSave);
            return true;
        }

        return false;
    }

    private boolean saveProduct(Product toSave) {
        basket.save(toSave);
        return true;
    }

    public boolean deleteProducts(String productName, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        Optional<Product> product = basket.findByName(productName);
        return product
            .map(p -> updateProductQuantity(p, quantity))
            .orElse(false);
    }

    private boolean updateProductQuantity(Product product, int quantity) {
        int quantityToUpdate = product.getQuantity() - quantity;
        if (quantityToUpdate >= 0 ) {
            updateBasket(product, quantityToUpdate);
            return true;
        }
        return false;
    }

    private void updateBasket(Product product, int quantity) {
        Product toUpdate = new Product(product.getName(), product.getPrice(), quantity);
        if (quantity == 0) {
            basket.deleteByName(product.getName());
        }
        basket.save(toUpdate);
    }

    public int getQuantityOfProduct(String productName) {
        Optional<Product> product = basket.findByName(productName);
        return product.map(Product::getQuantity).orElse(NO_PRODUCT);
    }

    public int getSumProductsPrices() {
        return basket.getAllProductsPricesSum();
    }

    public int getProductPrice(String productName) {
        Optional<Product> product = basket.findByName(productName);
        return product.map(Product::getPrice).orElse(NO_PRODUCT);
    }

    public List<String> getProductsNames() {
        return basket.findAll().stream()
            .map(Product::getName)
            .sorted()
            .collect(Collectors.toList());
    }
}
