/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.exception;

import javax.servlet.http.HttpServletResponse;


public class ValidationException extends AbstractApplicationException {

    
    public ValidationException(String s){
        super(s, HttpServletResponse.SC_BAD_REQUEST);
    }
    
}
