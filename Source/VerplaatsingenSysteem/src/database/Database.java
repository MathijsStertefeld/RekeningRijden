/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eagle
 */
public class Database
{

    private static Connection connection;

    
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String username = "PROFTAAK";
        String password = "wachtwoord";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void writeToDatabase(TimeStep timestep, Session session) throws ClassNotFoundException, SQLException
    {
        connection  = getConnection();
        Statement newStatement = connection.createStatement();
        newStatement.execute("SELECT * FROM Simsession WHERE startdate = " + session.getSessionDate().getTime());
        ResultSet newResultSet = newStatement.getResultSet();

        if (newResultSet.next() == false)
        {
            newSession(session);
        }

        newStatement.close();

        int timestepId = newTimeStep(timestep);

        // Check if the edges are in the database, if not add them to the database.
        ArrayList<Edge> edges = (ArrayList<Edge>) timestep.getEdges();

        for (int i = 0; i < edges.size(); i++)
        {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM Edge WHERE id='" + edges.get(i).getId() + "'");
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next() == false)
            {
                newEdge(edges.get(i));
            }

            ArrayList<Lane> lanes = (ArrayList<Lane>) edges.get(i).getLanes();

            for (int j = 0; j < lanes.size(); j++)
            {
                statement = connection.createStatement();
                statement.execute("SELECT * FROM Lane WHERE id ='" + lanes.get(j).getId() + "'");

                ResultSet resultSet1 = statement.getResultSet();

                // If the current lane is not found in the database add it to the database.
                if (resultSet1.next() == false)
                {
                    newLane(lanes.get(j), edges.get(i).getId());
                }

                ArrayList<VehiclePosition> vehiclePositions = (ArrayList<VehiclePosition>) lanes.get(j).getPositions();

                for (int k = 0; k < vehiclePositions.size(); k++)
                {
                    newVehiclePosition(vehiclePositions.get(k), lanes.get(j), timestepId);
                }
            }

            statement.close();
        }

        connection.close();
    }

    private static int newTimeStep(TimeStep timestep) throws ClassNotFoundException, SQLException
    {
        try
        {
            Statement statement = connection.createStatement();

            // Add the new timestep to the database.
            statement.executeUpdate("SELECT COUNT(*) FROM Timestep ");
            ResultSet temp = statement.getResultSet();

            int index = 0;

            while (temp.next())
            {
                index = temp.getInt(1);
                index++;
            }
            
            statement.executeUpdate("INSERT INTO Timestep(id, time, session_id) VALUES(" + index + "," + timestep.getTime() + "," + 1 + ")");

            statement.close();
            
            return index;
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new TIMESTEP to the database.");
            System.err.println("Exception " + e.toString());
        }
        
        return -1;
    }

    private static void newLane(Lane lane, String edgeId) throws ClassNotFoundException, SQLException
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Lane(id, edge_id) VALUES('" + lane.getId() + "','" + edgeId + "')");

            statement.close();
        }
        catch (Exception e)
        {
            System.err.println("With following lane: " + lane.getId() + " and edge: " + edgeId);
            System.err.println("Failed creating a new LANE to the database.");
            System.err.println("Exception " + e.toString());
        }
    }

    private static void newEdge(Edge edge) throws ClassNotFoundException, SQLException
    {
        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO Edge(id) VALUES('" + edge.getId() + "')");

            statement.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new EDGE to the database.");
            System.err.println("Exception " + e.toString());
        }
    }

    private static void newSession(Session session) throws ClassNotFoundException, SQLException
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO SIMSESSION(id, startdate) values(1," + session.getSessionDate().getTime() + ")");

            statement.close();
        }
        catch (Exception e)
        {
            System.err.println("Failed creating a new SESSION to the database.");
            System.err.println("Exception " + e.toString());
        }
    }

    private static void newVehiclePosition(VehiclePosition vehiclePosition, Lane lane, int timeStepId) throws ClassNotFoundException, SQLException
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO VehiclePosition(cartracker_id, position, speed, lane_id, timestep_id) VALUES('"
                    + vehiclePosition.getCarTrackerId() + "'," + vehiclePosition.getCarPos() + "," + vehiclePosition.getCarSpeed()
                    + ",'" + lane.getId() + "'," + timeStepId + ")");

            statement.close();
        }
        catch (Exception e)
        {
            System.err.println("With following vehiclePosition: " + vehiclePosition.getCarTrackerId());
            System.err.println("Failed creating a new VEHICLE POSITION to the database.");
            System.err.println("Exception " + e.toString());
        }
    }
}
