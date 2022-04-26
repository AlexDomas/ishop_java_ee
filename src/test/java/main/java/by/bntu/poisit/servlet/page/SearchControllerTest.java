package main.java.by.bntu.poisit.servlet.page;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.form.SearcherProductForm;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import main.java.by.bntu.poisit.servlet.AbstractController;
import static main.java.by.bntu.poisit.servlet.page.MyOrderControllerTest.path;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class SearchControllerTest extends AbstractController{
    
    public final static String path = "/WEB-INF/jsp/page/search-result.jsp";

    @Mock
    ServiceManager service;

    @Mock
    final HttpServletRequest req;

    @Mock
    final HttpServletResponse resp;

    @Mock
    final RequestDispatcher dispatcher;

    final SearchController searchController = new SearchController();

    public SearchControllerTest() {
        MockitoAnnotations.initMocks(this);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        service = mock(ServiceManager.class);
    }
    
    @Test
    public void testDoGetCallGetProductServiceMethodCountProductsBySearcherFormThenResultIsNotNull() throws Exception {
        SearcherProductForm sPF = createSearcherProductForm(req);

        int totalCount = service.getProductService().countProductsBySearcherProductForm(sPF);
        
        assertNotNull("totalCount is Null", totalCount);
    }

    @Test
    public void testDoGetCallGetProductServiceMethodThenResultIsTrue() throws Exception {
        SearcherProductForm sPF = createSearcherProductForm(req);

        verify(service, times(1)).getProductService().listProductsBySearcherProductForm(sPF, 1, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE);
        verify(service,times(1)).getProductService().countProductsBySearcherProductForm(sPF);
        int totalCount = service.getProductService().countProductsBySearcherProductForm(sPF);
        when(getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_PRODUCTS_PAGE)).thenReturn(totalCount);

    }
    
    @Test
    public void testDoGetAfterGetProductServiceCallForwardToPageThenResultIsSearchResultPage() throws Exception {
        searchController.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);
    }

}
