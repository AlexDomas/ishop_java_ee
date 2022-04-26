
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;

@WebServlet("/my-orders")
public class MyOrderController extends AbstractController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        List<Order> orders = getOrderService().listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orders);
        int orderCount = getOrderService().countMyOrders(currentAccount);
        req.setAttribute("pageCount", getPageCount(orderCount, Constants.ORDERS_PER_PAGE));
        RoutingUtils.forwardToPage("my-orders.jsp", req, resp);
        System.out.println("orders: " + orders);
    }
    
    
    
}
