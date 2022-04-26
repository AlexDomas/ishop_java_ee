/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.model.ShoppingCartItem;
import main.java.by.bntu.poisit.service.OrderService;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.util.SessionUtils;

@WebFilter(filterName = "AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter extends AbstractFilter {

    private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";

    private OrderService orderService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

        if (req.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(req)) { //если текущая корзина не существует
                Cookie cookie = SessionUtils.findShoppingCartCookie(req);
                if (cookie != null) { //если куки найдена
                    ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
                    if (shoppingCart != null) {
                        SessionUtils.setCurrentShoppingCart(req, shoppingCart);//сохраняем в текущую сессию
                    }
                }
            }
            req.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
        }
        chain.doFilter(req, resp);
    }

}
