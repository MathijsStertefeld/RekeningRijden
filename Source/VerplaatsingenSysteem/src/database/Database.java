/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static void doSomething(TimeStep timestep) throws ClassNotFoundException, SQLException
    {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        newTimeStep(timestep);
        
        // Check if the edges are in the database, if not add them to the database.
        ArrayList<Edge> edges = timestep.getEdges();

        for (int i = 0; i < edges.size(); i++)
        {
            if (statement.execute("SELECT * FROM Edge WHERE id = " + edges.get(i).getId()) == false)
            {
                newEdge(edges.get(i));
            }
            
           // ArrayList<Lane> lanes = edges.get(i).getLanes();
            
//            for (int j = 0; j < lanes.size(); j++)
//            {
//            }
        }
        


        statement.close();
        connection.close();
    }

    public static void newTimeStep(TimeStep timestep) throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            // Add the new timestep to the database.
            statement.executeUpdate("INSERT INTO Timestep(time, session_id) VALUES(" + timestep.getTime() + "," + "1)");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new TIMESTEP to the database.");
        }
    }

    public static void newVehiclePosition(VehiclePosition vehiclePosition) throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into VehiclePosition(carTracker_ID, position, speed, lane_id, timestep_time) values("
                    + vehiclePosition.getCarTrackerId() + "," + vehiclePosition.getCarPos() + "," + vehiclePosition.getCarSpeed()
                    + ",");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new VEHICLE POSITION to the database.");
        }
    }

    public static void newLane(Lane lane) throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Lane(id, edge_id) VALUES("+ lane.getId() + ")");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new LANE to the database.");
        }
    }

    public static void newEdge(Edge edge) throws ClassNotFoundException, SQLException
    {
        try
        {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            
            statement.executeUpdate("INSERT INTO Edge(id) VALUES("+ edge.getId() + ")");

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new EDGE to the database.");
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
