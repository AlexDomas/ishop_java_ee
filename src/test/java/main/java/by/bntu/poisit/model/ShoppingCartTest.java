
package main.java.by.bntu.poisit.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import main.java.by.bntu.poisit.entity.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


public class ShoppingCartTest {
    
    @Mock
    ShoppingCart shoppingCart;
    
    final private Map<Integer, ShoppingCartItem> products = new LinkedHashMap<>();
    
    private final Integer ID_PRODUCT = 123;
    private final String PRODUCT_NAME= "Apple";
    private final String DESCRIPTION = "very good choice";
    private final String IMAGE_LINK = "/media/honor.jpg";
    private final BigDecimal VALID_PRICE = new BigDecimal(1233);
    private final BigDecimal PRICE_NOT_VALID = new BigDecimal(1233321322);
    private final String CATEGORY = "Smartphone";
    private final String PRODUCER = "Sony";
    
    private final int CURRENT_COUNT_OF_PRODUCTS = 2;
    
    Product product = new Product(ID_PRODUCT,PRODUCT_NAME,DESCRIPTION ,IMAGE_LINK,VALID_PRICE, CATEGORY, PRODUCER);
    
    
    public ShoppingCartTest() {
        MockitoAnnotations.initMocks(this);
        shoppingCart = mock(ShoppingCart.class);
    }

    @Test
    public void testCallAddProductToShoppingCartThenResultSetCountShoppinCartItemIsTrueAndListProductsNotNull() {
        shoppingCart.checkShoppingCartSize(product.getId());
        ShoppingCartItem shoppingCartItem = products.get(product.getId());
        if (shoppingCartItem == null) {
            shoppingCart.checkProductCount(CURRENT_COUNT_OF_PRODUCTS);
            shoppingCartItem = new ShoppingCartItem(product, CURRENT_COUNT_OF_PRODUCTS);
            products.put(product.getId(), shoppingCartItem);
            verify(products, times(0)).put(product.getId(), shoppingCartItem);
        } else {
            shoppingCart.checkProductCount(CURRENT_COUNT_OF_PRODUCTS + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + CURRENT_COUNT_OF_PRODUCTS);
        }
        verify(shoppingCart, times(1)).refreshStatistics();
        assertNotNull("List products is Empty", products);
    }
    
    @Test
    public void testCallAddProductToShoppingCartThenResultIsCorrectAddedAndListOfProductsNotNull() {
        shoppingCart.checkShoppingCartSize(product.getId());
        ShoppingCartItem shoppingCartItem = null;
        if (shoppingCartItem == null) {
            shoppingCart.checkProductCount(CURRENT_COUNT_OF_PRODUCTS);
            shoppingCartItem = new ShoppingCartItem(product, CURRENT_COUNT_OF_PRODUCTS);
            products.put(product.getId(), shoppingCartItem);
            verify(products, times(1)).put(product.getId(), shoppingCartItem);
        } else {
            shoppingCart.checkProductCount(CURRENT_COUNT_OF_PRODUCTS + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + CURRENT_COUNT_OF_PRODUCTS);
        }
        verify(shoppingCart, times(1)).refreshStatistics();
        assertNotNull("List products is Empty", products);
    }

    @Test
    public void testCallRemoveProductWhenShoppingCartItemIsPresentThenResultIsCallRemoveItem() {
        ShoppingCartItem shoppingCartItem = products.get(ID_PRODUCT);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > CURRENT_COUNT_OF_PRODUCTS) {
                shoppingCartItem.setCount(shoppingCartItem.getCount() - CURRENT_COUNT_OF_PRODUCTS);
            } else {
                verify(products, times(1)).remove(ID_PRODUCT); 
            }
            verify(shoppingCart, times(1)).refreshStatistics();
        }
        assertFalse("Products list contains ID_PRODUCT" , products.containsKey(ID_PRODUCT));
    }
    
    @Test
    public void testCallRemoveProductWhenShoppingCartItemIsPresentThenResultListContainsIdProduct() {
        ShoppingCartItem shoppingCartItem = null;
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > CURRENT_COUNT_OF_PRODUCTS) {
                shoppingCartItem.setCount(shoppingCartItem.getCount() - CURRENT_COUNT_OF_PRODUCTS);
            } else {
                verify(products, times(1)).remove(ID_PRODUCT); 
            }
            verify(shoppingCart, times(1)).refreshStatistics();
        }
        assertTrue("Products list doesn't contain ID_PRODUCT", products.containsKey(ID_PRODUCT));
    }

    

    
    
}
