package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.util.RoutingUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class AboutUsControllerTest {

    public final static String path = "/WEB-INF/jsp/page/about-us.jsp";

    @Test
    public void whenCallDoGetThenServletReturnPageUrlAboutUs() throws ServletException, IOException {
        final AboutUsController servlet = new AboutUsController();

        final HttpServletRequest req = mock(HttpServletRequest.class);

        final HttpServletResponse resp = mock(HttpServletResponse.class);

        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        RoutingUtils.forwardToPage(path, req, resp);

        verify(req, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(req, resp);

    }
}
