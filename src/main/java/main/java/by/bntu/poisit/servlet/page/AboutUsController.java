
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;

@WebServlet("/about-us")
public class AboutUsController extends AbstractController{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        RoutingUtils.forwardToPage("about-us.jsp", req, resp);
    }
    
}
