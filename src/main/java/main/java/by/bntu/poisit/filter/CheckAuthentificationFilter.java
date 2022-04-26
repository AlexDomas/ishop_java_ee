
package main.java.by.bntu.poisit.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;
import main.java.by.bntu.poisit.util.UrlUtils;
import main.java.by.bntu.poisit.util.WebUtils;

@WebFilter(filterName = "CheckAuthentificationFilter")
public class CheckAuthentificationFilter extends AbstractFilter{

   
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, 
            FilterChain chain) throws IOException, ServletException {
        
        if(SessionUtils.isCurrentAccountCreated(req)){
            chain.doFilter(req, resp);
        }else{
            String requestUrl = WebUtils.getCurrentRequestUrl(req);
            if(UrlUtils.isAjaxUrl(requestUrl)){
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("401");
            }else{
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, requestUrl);
                RoutingUtils.redirect("/sign-in", req, resp);
            }
        }
        
    }
    
}
