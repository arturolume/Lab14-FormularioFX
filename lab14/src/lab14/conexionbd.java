
package lab14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Arturo Lume
 */
public class conexionbd {
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula","root","Reyes562"); 
        
        return connection;
    } 
}
