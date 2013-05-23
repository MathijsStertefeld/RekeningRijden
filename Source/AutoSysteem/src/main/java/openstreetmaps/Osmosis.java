/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openstreetmaps;

import domain.Edge;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.*;
import org.openstreetmap.osmosis.core.task.v0_6.*;
import org.openstreetmap.osmosis.xml.common.CompressionMethod;
import org.openstreetmap.osmosis.xml.v0_6.XmlReader;

/**
 *
 * @author Leslie Aerts
 */
public class Osmosis
{

    public static List<Node> getNodes()
    {
        return nodes;
    }

    public static List<Way> getWays()
    {
        return ways;
    }

    public static List<Relation> getRelations()
    {
        return relations;
    }
    
    private static List<Node> nodes;
    private static List<Way> ways;
    private static List<Relation> relations;

    public static void init()
    {
        nodes = new ArrayList<Node>();
        ways = new ArrayList<Way>();
        relations = new ArrayList<Relation>();

        parseFile(new File("bigroads.xml"));
    }

    public static void parseFile(File f)
    {
        File file = f;// the input file

        Sink sinkImplementation = new Sink()
        {
            public void process(EntityContainer entityContainer)
            {
                Entity entity = entityContainer.getEntity();
                if (entity instanceof Node)
                {
                    Node node = (Node) entity;
                    nodes.add(node);
                }
                else if (entity instanceof Way)
                {
                    Way way = (Way) entity;
                    ways.add(way);
                }
                else if (entity instanceof Relation)
                {
                    Relation relation = (Relation) entity;
                    relations.add(relation);
                }
            }

            @Override
            public void initialize(Map<String, Object> map)
            {
                //    throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void complete()
            {
                //           throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void release()
            {
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        CompressionMethod compression = CompressionMethod.None;

        RunnableSource reader;

        reader = new XmlReader(file, false, compression);

        reader.setSink(sinkImplementation);

        Thread readerThread = new Thread(reader);
        readerThread.start();

        while (readerThread.isAlive())
        {
            try
            {
                readerThread.join();
            }
            catch (InterruptedException e)
            {
                /* do nothing */
            }
        }
    }

    /**
     *
     * Get the nodes that are connected to this way
     *
     * @param way The given Way
     * @return list of nodes
     */
    public static List<Node> getNodesAttachedToWay(Way way)
    {
        List<WayNode> wayNodes = way.getWayNodes();

        List<Node> nodesAttachedToWay = new ArrayList<Node>();

        for (WayNode wN : wayNodes)
        {
            for (int i = 0; i < nodes.size(); i++)
            {
                Node node = nodes.get(i);

                if (wN.getNodeId() == node.getId())
                {
                    nodesAttachedToWay.add(node);
                }
            }
        }

        return nodesAttachedToWay;
    }

    /**
     *
     * Get a value of the given Way:
     *
     * Examples: "highway" - The type of highway the Way is "lanes" - The number
     * of lanes the Way has "loc_name" - The location name of the Way "name" -
     * The name of the Way "maxspeed" - The maxSpeed that is set for this Way
     * "oneway" - If the Way is oneway or not "ref" - The chain of roads this
     * Way is part of
     *
     * @param way The way to get the value from
     * @param key The key for the value you want to retrieve
     * @return The value requested for the given key
     */
    public static String getValueOfWay(Way way, String key)
    {
        Collection<Tag> tags = way.getTags();
        String result = "";

        for (Tag t : tags)
        {
            if (t.getKey().equals(key))
            {
                result = t.getValue();
            }
        }

        return result;
    }

    public static Long getWayId(Way way)
    {
        return way.getId();
    }

    public static Long getNodeId(Node node)
    {
        return node.getId();
    }

    public static Edge createNewEdgeFromWay(Way way)
    {
        Edge edge = new Edge();
        edge.setId(Long.toString(way.getId()));
        return edge;
    }
}
