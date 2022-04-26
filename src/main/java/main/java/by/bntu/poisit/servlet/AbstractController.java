/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.form.SearcherProductForm;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.service.OrderService;
import main.java.by.bntu.poisit.service.ProductService;
import main.java.by.bntu.poisit.service.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractController extends HttpServlet {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    private ProductService productService;
    private OrderService orderService;
    private SocialService socialService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
        socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
    }

    public final ProductService getProductService() {
        return productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }

    public final SocialService getSocialService() {
        return socialService;
    }

    public final int getPageCount(int totalCount, int itemsPerPage) {
        int res = totalCount / itemsPerPage;
        if (res * itemsPerPage != totalCount) {
            res++;
        }
        return res;
    }

    public final int getPage(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    
    public final SearcherProductForm createSearcherProductForm(HttpServletRequest request){
        return new SearcherProductForm(request.getParameter("query"),
                request.getParameterValues("category"), 
                request.getParameterValues("producer"));
        
    }
    
    public final ProductForm createProductForm(HttpServletRequest request){
        return new ProductForm(Integer.parseInt(request.getParameter("idProduct")),
        Integer.parseInt(request.getParameter("count")));
        
    }
        

}
