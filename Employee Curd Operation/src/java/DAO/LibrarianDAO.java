package DAO;

import POJO.LibrarianPOJO;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarianDAO {

    public static int insert(LibrarianPOJO pojo) throws SQLException {
        Connection conn = null;
        int rowInserted = 0;
        String sql = "INSERT INTO e_librarian (name,email,password,mobile) VALUES (?,?,?,?)";
        try {
            // load jdbc driver
            Class.forName("com.mysql.jdbc.Driver");
            // create connection object to establish connection between application and database
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");

            // step-2 create preapred statement
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, pojo.getName());
            stm.setString(2, pojo.getEmail());
            stm.setString(3, pojo.getPassword());
            stm.setLong(4, pojo.getMobile());
            rowInserted = stm.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close(); 
        }
        return (rowInserted);
    }

    public static List<LibrarianPOJO> select() throws SQLException {
        Connection conn = null;
        List<LibrarianPOJO> lib = new ArrayList<>();
        String sqlQuery = "SELECT * FROM e_librarian";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");
            PreparedStatement stm = conn.prepareStatement(sqlQuery);
            ResultSet rows = stm.executeQuery();
            while (rows.next()) {
                LibrarianPOJO pojo = new LibrarianPOJO();
                pojo.setId(rows.getInt("id"));
                pojo.setName(rows.getString("name"));
                pojo.setEmail(rows.getString("email"));
                pojo.setMobile(rows.getLong("mobile"));
                pojo.setPassword(rows.getString("password"));
                pojo.setUpdated(rows.getTimestamp("updated_time").toString());
                lib.add(pojo);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.close();
        }
        return lib;
    }
    
    public static LibrarianPOJO getById(int id) throws SQLException{
        Connection conn = null;
        LibrarianPOJO pojo = new LibrarianPOJO();
        String sql = "SELECT * FROM e_librarian WHERE id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,id);
            ResultSet set = stm.executeQuery();
            if(set.next()){
                pojo.setId(set.getInt("id"));
                pojo.setName(set.getString("name"));
                pojo.setEmail(set.getString("email"));
                pojo.setPassword(set.getString("password"));
                pojo.setMobile(set.getLong("mobile"));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.close();
        }
        return pojo;
    }
    
    public static void update(LibrarianPOJO pojo) throws SQLException{
        Connection conn = null;
        String sql = "UPDATE e_librarian SET name=?,email=?,password=?,mobile=? WHERE id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,pojo.getName());
            stm.setString(2, pojo.getEmail());
            stm.setString(3, pojo.getPassword());
            stm.setLong(4, pojo.getMobile());
            stm.setInt(5, pojo.getId());
            System.out.println(stm.executeUpdate());
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.close();
        }
    }
    
    public static void delete(int id) throws SQLException{
        Connection conn = null;
        String sql = "DELETE FROM e_librarian WHERE id=?";
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,id);
            System.out.println(stm.executeUpdate()); 
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.close();
        }
    }
    
    public static List search(String searchChar) throws SQLException{
        String sqlQuery = "SELECT * FROM e_librarian WHERE name LIKE '%"+searchChar+"%'";
        Connection conn = null;
        List<LibrarianPOJO> lib = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase?user=root");
            PreparedStatement stm = conn.prepareStatement(sqlQuery);
            ResultSet rows = stm.executeQuery();
            while (rows.next()) {
                LibrarianPOJO pojo = new LibrarianPOJO();
                pojo.setId(rows.getInt("id"));
                pojo.setName(rows.getString("name"));
                pojo.setEmail(rows.getString("email"));
                pojo.setMobile(rows.getLong("mobile"));
                pojo.setPassword(rows.getString("password"));
                pojo.setUpdated(rows.getTimestamp("updated_time").toString());
                lib.add(pojo);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrarianDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conn.close();
        }
        return lib;        
    } 
}
