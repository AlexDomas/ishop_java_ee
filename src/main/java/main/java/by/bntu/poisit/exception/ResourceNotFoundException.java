
package main.java.by.bntu.poisit.exception;

import javax.servlet.http.HttpServletResponse;


public class ResourceNotFoundException extends AbstractApplicationException{

    public ResourceNotFoundException(String s) {
        super(s, HttpServletResponse.SC_NOT_FOUND);
    }

    
}
