
package main.java.by.bntu.poisit.servlet.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;
import org.json.JSONObject;


public abstract class AbstractProductController extends AbstractController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm form = createProductForm(req);
        ShoppingCart shoppingCart = getCurrentShoppingCart(req);
        processProductForm(form, shoppingCart, req, resp);
        if(!SessionUtils.isCurrentAccountCreated(req)){
            SessionUtils.setCurrentShoppingCart(req, shoppingCart);
        }
        sendResponse(shoppingCart, req, resp);
    }
    
    private ShoppingCart getCurrentShoppingCart(HttpServletRequest req){
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }
    
    protected abstract void processProductForm(ProductForm form, 
            ShoppingCart shoppingCart,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    
    protected void sendResponse(ShoppingCart shoppingCart,HttpServletRequest req, HttpServletResponse resp) throws IOException{
        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        cardStatistics.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtils.sendJSON(cardStatistics, req, resp);
    }
    
    
}
