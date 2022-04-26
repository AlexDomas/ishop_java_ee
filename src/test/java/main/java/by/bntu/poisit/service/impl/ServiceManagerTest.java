/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.service.impl;

import javax.servlet.ServletContext;
import main.java.by.bntu.poisit.service.OrderService;
import main.java.by.bntu.poisit.service.ProductService;
import main.java.by.bntu.poisit.service.SocialService;
import static main.java.by.bntu.poisit.servlet.page.AllProductsListControllerTest.path;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

public class ServiceManagerTest {
    
    @Mock
    ServiceManager service;

    public ServiceManagerTest() {
    }

   
    @Test
    public void testGetProductService() {
        
      
    }

    @Test
    public void testGetOrderService() {
        System.out.println("getOrderService");
        ServiceManager instance = null;
        OrderService expResult = null;
        OrderService result = instance.getOrderService();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSocialService() {
        System.out.println("getSocialService");
        ServiceManager instance = null;
        SocialService expResult = null;
        SocialService result = instance.getSocialService();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetApplicationProperty() {
        System.out.println("getApplicationProperty");
        String key = "";
        ServiceManager instance = null;
        String expResult = "";
        String result = instance.getApplicationProperty(key);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }


}
