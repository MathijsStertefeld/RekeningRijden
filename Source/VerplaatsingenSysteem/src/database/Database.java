/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Eagle
 */
public class Database
{
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "PROFTAAK";
        String password = "wachtwoord";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void newMovement() throws ClassNotFoundException, SQLException
    {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into Movement(timestamp) values(10.0)");
        
        statement.close();
        connection.close();
    }
}
