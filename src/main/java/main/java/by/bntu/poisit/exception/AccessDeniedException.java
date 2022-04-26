
package main.java.by.bntu.poisit.exception;

import javax.servlet.http.HttpServletResponse;


public class AccessDeniedException extends AbstractApplicationException{

    public AccessDeniedException(String s) {
        super(s, HttpServletResponse.SC_FORBIDDEN);
    }
    
}
