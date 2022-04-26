/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.servlet.page;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.servlet.AbstractController;
import main.java.by.bntu.poisit.util.RoutingUtils;


@WebServlet("/error")
public class ErrorHandlerController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("statusCode", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        RoutingUtils.forwardToPage("error.jsp", req, resp);
    }
}
