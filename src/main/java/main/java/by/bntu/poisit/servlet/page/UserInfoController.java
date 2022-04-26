package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.InputIdUtils;
import main.java.by.bntu.poisit.util.RoutingUtils;

@WebServlet("/user-info")
public class UserInfoController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allUsers", getOrderService().listAllUsers());
        RoutingUtils.forwardToPage("user-info.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        InputIdUtils iPUtils = new InputIdUtils();

        StringBuilder errorMessage = iPUtils.generateErrorMessageForAdminPanel(id);

        req.setAttribute("allUsers", getOrderService().listAllUsers());
        
        req.setAttribute("errorId", errorMessage.toString());

        if (iPUtils.isValidID(id)) {
            if (getOrderService().findIdFromUser(id) != 0) {
                getOrderService().deleteUserFromSystem(id);
                req.setAttribute("errorIdRepeat", ErrorConstant.STR_CORRECT_DELETE_USER);
            } else {

                req.setAttribute("errorIdRepeat", ErrorConstant.STR_NO_USER_WITH_ID);

            }

        }

        RoutingUtils.forwardToPage("user-info.jsp", req, resp);

    }
}