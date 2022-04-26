/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;

@WebServlet("/products")
public class AllProductsListController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<?> products = getProductService().listAllProducts(1, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE); //get Products from database
        req.setAttribute("products", products);
        int totalCount = getProductService().countAllProducts();
        req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE));
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
