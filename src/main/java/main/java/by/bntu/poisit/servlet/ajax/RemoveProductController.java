
package main.java.by.bntu.poisit.servlet.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.util.SessionUtils;

@WebServlet("/ajax/json/product/remove")
public class RemoveProductController extends AbstractProductController{

    @Override
    protected void processProductForm(ProductForm form, ShoppingCart shoppingCart, 
            HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        getOrderService().removeProductFromShoppingCart(form, shoppingCart);
        if(shoppingCart.getItems().isEmpty()){
            SessionUtils.clearCurrentShoppingCart(req, resp);
        }else{
            String cookieValue = getOrderService().serializeShoppingCart(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
              
        
    }
    
    
}
