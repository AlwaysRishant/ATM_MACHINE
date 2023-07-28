/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtmMachine.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class dbConnection {
    public static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-9KGN5MS:1521/XE","atm", "machine"); 
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Error in driver loading"+ex.getMessage());
            ex.printStackTrace();
        }   
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error in opening the connection to DB"+ex.getMessage());
            ex.printStackTrace();
        }   
    }
    public static Connection getConnection()
    {  
        return conn;
    }
    public static void CloseConnection()
    {
        try{
            conn.close();
        }
        catch(SQLException ex)
        {
             JOptionPane.showMessageDialog(null,"Error in Disconnect to DB"+ex.getMessage());
             ex.printStackTrace();
        }
    }
}

