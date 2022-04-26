/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import main.java.by.bntu.poisit.constants.Constants;
import main.java.by.bntu.poisit.service.impl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
            sce.getServletContext().setAttribute(Constants.CATEGORY_LIST, serviceManager.getProductService().listAllCategories());
            sce.getServletContext().setAttribute(Constants.PRODUCER_LIST, serviceManager.getProductService().listAllProducers());

        } catch (RuntimeException e) {
            LOGGER.error("Web application init failed: " + e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Web application initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        serviceManager.close();
        LOGGER.info("Web application destroyed");
    }

}
