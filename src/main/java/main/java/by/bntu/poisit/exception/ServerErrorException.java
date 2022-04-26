
package main.java.by.bntu.poisit.exception;

import javax.servlet.http.HttpServletResponse;


public class ServerErrorException extends AbstractApplicationException{

    public ServerErrorException(String message) {
        super(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public ServerErrorException(Throwable cause) {
        super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    
    
    
}
