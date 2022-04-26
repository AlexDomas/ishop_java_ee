/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import static main.java.by.bntu.poisit.servlet.page.AboutUsControllerTest.path;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;


public class AllProductsListControllerTest {
    
    public final static String path = "/WEB-INF/jsp/page/products.jsp";

    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;
    
    public AllProductsListControllerTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
    }
    
    final AllProductsListController controllerAllProducts = new AllProductsListController();
 
    @Test
    public void testWhenCallProductServiceMethodCountAllProductsThenTotalCountEqualsNotNull() throws Exception {
        int totalCount = service.getProductService().countAllProducts();
        assertNotNull("TotalCount is null",totalCount);
    }
   

    @Test
    public void testWhenCallDoGetThenCallMethodsFromGetProductServiceAndForwardToPage() throws Exception {
 
        
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);

        controllerAllProducts.doGet(req, resp);
        
        verify(service,times(1)).getProductService().listAllProducts(1, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE); 
        verify(service,times(1)).getProductService().countAllProducts();

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }
    
}
