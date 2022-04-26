/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;


@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController {
    
    private static final int SUBSTRING_INDEX = "/products".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = getProductService().listProductsByCategory(categoryUrl, 1, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE);
        req.setAttribute("products", products);
        int totalCount = getProductService().countProductsByCategory(categoryUrl);
        req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE));
        req.setAttribute("selectedCategoryUrl", categoryUrl);
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}