/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

@WebServlet("/delete-product")
public class DeleteProductFromSystem extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RoutingUtils.forwardToPage("delete-product.jsp", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idPr = Integer.parseInt(req.getParameter("id_pr"));

        InputIdUtils iPUtils = new InputIdUtils();

        StringBuilder errorMessage = iPUtils.generateErrorMessageForAdminPanel(idPr);

        req.setAttribute("errorId", errorMessage.toString());

        if (iPUtils.isValidID(idPr)) {
            if (getProductService().findIdFromProduct(idPr) != 0) {

                getProductService().deleteProductFromSystem(idPr);
                
                req.setAttribute("errorIdRepeat", ErrorConstant.STR_CORRECT_DELETE);

            } else {
                req.setAttribute("errorIdRepeat", ErrorConstant.STR_NO_PRODUCT_WITH_ID);

            }
        }
        RoutingUtils.forwardToPage("delete-product.jsp", req, resp);

    }

}
