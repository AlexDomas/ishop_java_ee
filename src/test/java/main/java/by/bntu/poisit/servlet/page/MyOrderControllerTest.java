/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class MyOrderControllerTest{
    
    public final static String path = "/WEB-INF/jsp/page/my-orders.jsp";
    
    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;
   
    final MyOrderController myOrderController = new MyOrderController();
    
    
    public MyOrderControllerTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
    }
    
    @Test
    public void testDoGetOrderServiceCountMyOrdersThenOrderCountIsNotNull() throws Exception{
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        int orderCount = service.getOrderService().countMyOrders(currentAccount);
        assertNotNull("OrderCount is Null", orderCount);
    }
    
    
    @Test 
    public void testDoGetOrderServiceListMyOrdersThenListOrdersCountIsNotNull() throws Exception{
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        List<Order> orders = service.getOrderService().listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        assertNotNull("ListOrders is Null", orders);
    }

    @Test
    public void testDoGetToCallGetOrderServiceMethodsOrdersThenForwardToPageMyOrders() throws Exception {
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        verify(service).getOrderService().listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        verify(service).getOrderService().countMyOrders(currentAccount);
        
        myOrderController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }
    
}
