package main.java.by.bntu.poisit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.exception.ValidationException;


public final class ShoppingCart implements Serializable{

    private static final long serialVersionUID = 1535770438453611801L;
    private Map<Integer, ShoppingCartItem> products = new LinkedHashMap<>();
    private int totalCount = 0;
    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addProduct(Product product, int count) {
        checkShoppingCartSize(product.getId());
        ShoppingCartItem shoppingCartItem = products.get(product.getId());
        if (shoppingCartItem == null) {
            checkProductCount(count);
            shoppingCartItem = new ShoppingCartItem(product, count);
            products.put(product.getId(), shoppingCartItem);
        } else {
            checkProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
        }
        refreshStatistics();
    }

    public void removeProduct(Integer idProduct, int count) {
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > count) {
                shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
            } else {
                products.remove(idProduct); //del all product
            }
            refreshStatistics();
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
    
    
    public void checkProductCount(int count) {
        if (count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART) {
            throw new ValidationException("Limit for product count reached: count=" + count);
        }
    }

    public void checkShoppingCartSize(int idProduct) {
        if (products.size() > Constants.MAX_PRODUCTS_PER_SHOPPING_CART
                || (products.size() == Constants.MAX_PRODUCTS_PER_SHOPPING_CART
                && !products.containsKey(idProduct))) {
            throw new ValidationException("Limit for ShoppingCart size reached: size= " + products.size());
        }
    }

    public void refreshStatistics() {
        totalCount = 0;
        totalCost = BigDecimal.ZERO;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
                totalCount += shoppingCartItem.getCount();
                totalCost = totalCost.add(shoppingCartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
            
        }
    }

    @Override
    public String toString() {
        return String.format("ShoppingCart [products=%s, totalCount=%s, totalCost=%s]", products, totalCount, totalCost);
    }

    
}
