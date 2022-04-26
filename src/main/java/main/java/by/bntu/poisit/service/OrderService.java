
package main.java.by.bntu.poisit.service;

import java.util.List;
import main.java.by.bntu.poisit.entity.Account;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.model.SocialAccount;


public interface OrderService {
    
    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    
    void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart);
    
    String serializeShoppingCart(ShoppingCart shoppingCart);
    
    ShoppingCart deserializeShoppingCart(String string);
    
    CurrentAccount authentificate(SocialAccount socialAccount);
    
    long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);
    
    Order findOrderById(long id, CurrentAccount currentAccount);
    
    List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit);
    
    int countMyOrders(CurrentAccount currentAccount);
    
    List<Account> listAllUsers();
    
    int findIdFromUser(Integer userID);
    
    void deleteUserFromSystem(int id);
    
}
