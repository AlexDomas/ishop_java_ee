
package main.java.by.bntu.poisit.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetHandler<T> {
    
    T handle(ResultSet rs) throws SQLException; //преобразование ResultSet в соответствующий объект
    
}
