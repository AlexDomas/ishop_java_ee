package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.model.SocialAccount;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;
import main.java.by.bntu.poisit.util.SessionUtils;

@WebServlet("/from-social")
public class FromSocialController extends AbstractController { //get answer from facebook, registr or not

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            SocialAccount socialAccount = getSocialService().getSocialAccount(code);
            CurrentAccount currentAccount = getOrderService().authentificate(socialAccount);
            SessionUtils.setCurrentAccount(req, currentAccount);
            redirectToCorrectPage(req, resp);
        } else {
            LOGGER.warn("Parameter code not found");
            if(req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN) != null){
                req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            }
            RoutingUtils.redirect("/sign-in", req, resp);
        }
    }
    
    protected void redirectToCorrectPage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
        if(targetUrl != null){
            req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            RoutingUtils.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp);
        }else{
            RoutingUtils.redirect("/my-orders", req, resp);
        }
    }
}
