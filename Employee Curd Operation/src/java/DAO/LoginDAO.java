
package DAO;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

    public static boolean isUser(String username, String password) throws SQLException {
        Connection conn = null;
        boolean isUse = false;
        String sql = "SELECT * FROM user WHERE name = ? AND password= ?";
        try {
            // load jdbc driver
            Class.forName("com.mysql.jdbc.Driver");
            // create connection object to establish connection between application and database
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");

            // step-2 create preapred statement
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet set = stm.executeQuery();
            
            if(set.next()){
                isUse = true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close(); 
        }
        return isUse;
    }
    
}
