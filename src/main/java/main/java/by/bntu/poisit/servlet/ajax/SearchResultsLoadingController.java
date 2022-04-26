/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.form.SearcherProductForm;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;

@WebServlet("/ajax/html/more/search")
public class SearchResultsLoadingController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SearcherProductForm sPF = createSearcherProductForm(req);
        
        List<?> products = getProductService().listProductsBySearcherProductForm(sPF, getPage(req), Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE);
        req.setAttribute("products", products);
        
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
    }
}
