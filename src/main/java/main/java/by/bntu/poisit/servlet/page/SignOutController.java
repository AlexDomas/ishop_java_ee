
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;

@WebServlet("/sign-out")
public class SignOutController extends AbstractController{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().invalidate();
        RoutingUtils.redirect("/products", req, resp);
    }
    
}
