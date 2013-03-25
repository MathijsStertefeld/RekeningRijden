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

    public static void newVehiclePosition() throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into VehiclePosition(position, speed, lane_id) values()");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new VEHICLE POSITION to the database.");
        }
    }

    public static void newTimeStep() throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Lane(lane) values(10.0)");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new TIMESTEP to the database.");
        }
    }
    
    public static void newSession() throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Lane(lane) values(10.0)");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new SESSION to the database.");
        }
    }
}
