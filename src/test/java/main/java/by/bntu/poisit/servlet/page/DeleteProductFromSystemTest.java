
package main.java.by.bntu.poisit.servlet.page;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.ErrorConstant;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.util.InputIdUtils;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class DeleteProductFromSystemTest {
    
     public final static String path = "/WEB-INF/jsp/page/delete-product.jsp";
    
    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;
    
    public static final Integer validIdProduct = 123;
    public static final Integer idProductIsNotValid = 1234567;
    
    
    public DeleteProductFromSystemTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
        
    }
    
    final DeleteProductFromSystem delProductFromSystController = new DeleteProductFromSystem();
 
    @Test
    public void testDoPostWithCorrectIdProductParameterThenCallGetProductServiceMethodDeleteProductFromSystem() throws Exception {
        

        InputIdUtils iPUtils = new InputIdUtils();

        if (iPUtils.isValidID(validIdProduct)) {
            if (service.getProductService().findIdFromProduct(validIdProduct) != 0) {

                verify(service, times(1)).getProductService().deleteProductFromSystem(validIdProduct);
         
            } 
        }
        delProductFromSystController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }
    
    @Test
    public void testDoPostWithIncorrectIdProductParameterThenCallOnlyForwardToPageDeleteProduct() throws Exception {
        InputIdUtils iPUtils = new InputIdUtils();

        if (iPUtils.isValidID(idProductIsNotValid)) {
            if (service.getProductService().findIdFromProduct(idProductIsNotValid) != 0) {

                verify(service, times(0)).getProductService().deleteProductFromSystem(idProductIsNotValid);
         
            } 
        }
        delProductFromSystController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }
    
}
