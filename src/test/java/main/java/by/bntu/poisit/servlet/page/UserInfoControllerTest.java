/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import static main.java.by.bntu.poisit.servlet.page.DeleteProductFromSystemTest.path;
import static main.java.by.bntu.poisit.servlet.page.DeleteProductFromSystemTest.validIdProduct;
import main.java.by.bntu.poisit.util.InputIdUtils;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class UserInfoControllerTest {
    
    public final static String path = "/WEB-INF/jsp/page/user-info.jsp";

    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;
    
     public static final Integer validIdUser = 123;
    public static final Integer idUserIsNotValid = 1234567;
    
    public UserInfoControllerTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
    }
    
    
    final UserInfoController userInfoController = new UserInfoController();

    @Test
    public void testDoPostWithCorrectIdUserParameterThenCallGetOrderServiceMethodDeleteUserFromSystem() throws Exception {
        
        InputIdUtils iPUtils = new InputIdUtils();

        if (iPUtils.isValidID(validIdUser)) {
            if (service.getProductService().findIdFromProduct(validIdUser) != 0) {

                verify(service, times(1)).getOrderService().deleteUserFromSystem(validIdUser);
         
            } 
        }
        userInfoController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void testDoPostWithIncorrectIdUserParameterThenCallOnlyForwardToPageDeleteProduct() throws Exception {
        
        InputIdUtils iPUtils = new InputIdUtils();

        if (iPUtils.isValidID(idUserIsNotValid)) {
            if (service.getProductService().findIdFromProduct(idUserIsNotValid) != 0) {

                verify(service, times(0)).getOrderService().deleteUserFromSystem(idUserIsNotValid);
         
            } 
        }
        userInfoController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }
    
}
