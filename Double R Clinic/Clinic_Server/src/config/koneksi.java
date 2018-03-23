/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author user
 */
public class koneksi {
    public Connection con;
    
    public void openConnection(){
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://GR-PC;databaseName=Clinic";
//            con = DriverManager.getConnection(url,"sa","123456");
            String url = "jdbc:sqlserver://DESKTOP-MEO5I55\\DHADOTID;databaseName=YR";
            con = DriverManager.getConnection(url,"sa","qwerty");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
