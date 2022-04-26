/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.util.InputProductUtils;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author User
 */
public class AddProductToSystemControllerTest {

    public final static String path = "/WEB-INF/jsp/page/add-product.jsp";

    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;

    private final Integer ID_PRODUCT = 123;
    private final String PRODUCT_NAME= "Apple";
    private final String DESCRIPTION = "very good choice";
    private final String IMAGE_LINK = "/media/honor.jpg";
    private final BigDecimal VALID_PRICE = new BigDecimal(1233);
    private final BigDecimal PRICE_NOT_VALID = new BigDecimal(1233321322);
    private final String CATEGORY = "Smartphone";
    private final String PRODUCER = "Sony";

    public AddProductToSystemControllerTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
    }

    @Test
    public void whenCallDoGetThenControllerReturnPageUrlAddProduct() throws Exception {

        final AddProductToSystemController addPrInstance = new AddProductToSystemController();
        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);

        addPrInstance.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);

    }

    @Test
    public void testDoPostWhenSetCorrectParametersProductThenCallProductServiceMethodAddProductToSystem() throws Exception {
        final AddProductToSystemController addPrInstance = new AddProductToSystemController();
        final InputProductUtils iPUtils = new InputProductUtils();
        Product product = new Product(ID_PRODUCT,PRODUCT_NAME,DESCRIPTION ,IMAGE_LINK,VALID_PRICE, CATEGORY, PRODUCER);

        if (iPUtils.isValidProductID(ID_PRODUCT) && iPUtils.isValidProductName(PRODUCT_NAME)
                && iPUtils.isValidDescription(DESCRIPTION) && iPUtils.isValidPrice(VALID_PRICE)) {
            if (service.getProductService().findIdFromProductForCheckEmpty(ID_PRODUCT) == 0
                    && service.getProductService().findNameFromProductForCheckEmpty(PRODUCT_NAME) == null) {
                addPrInstance.doPost(req, resp);
                verify(service, times(1)).getProductService().addProductToSystem(product, 1, 1);
            }

        }
        addPrInstance.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);

    }

    @Test
    public void testDoPostWhenSetIncorrectParameterProductThenCallForwardToPage() throws Exception {
        final AddProductToSystemController addPrInstance = new AddProductToSystemController();
        final InputProductUtils iPUtils = new InputProductUtils();
        Product product = new Product(ID_PRODUCT,PRODUCT_NAME,DESCRIPTION ,IMAGE_LINK,VALID_PRICE, CATEGORY, PRODUCER);

        if (iPUtils.isValidProductID(ID_PRODUCT) && iPUtils.isValidProductName(PRODUCT_NAME)
                && iPUtils.isValidDescription(DESCRIPTION) && iPUtils.isValidPrice(PRICE_NOT_VALID)) {
            if (service.getProductService().findIdFromProductForCheckEmpty(ID_PRODUCT) == 0
                    && service.getProductService().findNameFromProductForCheckEmpty(PRODUCT_NAME) == null) {
                addPrInstance.doPost(req, resp);
                verify(service, times(1)).getProductService().addProductToSystem(product, 1, 1);
            }
        }
        addPrInstance.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }

}
