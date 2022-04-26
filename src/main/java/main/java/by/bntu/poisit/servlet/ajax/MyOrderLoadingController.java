
package main.java.by.bntu.poisit.servlet.ajax;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;


@WebServlet("/ajax/html/more/my-orders")
public class MyOrderLoadingController extends AbstractController{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Order> orders = getOrderService().listMyOrders(SessionUtils.getCurrentAccount(req), getPage(req), Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orders);
        RoutingUtils.forwardToFragment("my-orders-tbody.jsp", req, resp);
    }
    
    
    
}
