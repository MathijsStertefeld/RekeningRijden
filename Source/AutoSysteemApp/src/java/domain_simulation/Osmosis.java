/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain.Edge;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
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

    public static ArrayList<Node> nodes;
    public static ArrayList<Way> ways;
    public static ArrayList<Relation> relations;

    public static void init()
    {
        nodes = new ArrayList<Node>();
        ways = new ArrayList<Way>();
        relations = new ArrayList<Relation>();

        parseFile(new File("bigroads.xml"));
    }

    public static ArrayList<Node> getNodes()
    {
        return nodes;
    }

    public static ArrayList<Way> getWays()
    {
        return ways;
    }

    public static ArrayList<Relation> getRelations()
    {
        return relations;
    }

    private static Node getNode(long id)
    {
        for (Node n : nodes)
        {
            if (n.getId() == id)
            {
                return n;
            }
        }
        return null;
    }

    public static ArrayList<Node> plotPath(int amountOfWays)
    {
        ArrayList<Node> routeNodes = new ArrayList<Node>();
        ArrayList<Way> remainingWays = ways;
        Way currentWay = null;
        long startNodeId = 0;

        for (int wayInt = 0; wayInt < amountOfWays; wayInt++)
        {
            Node startNode = null;
            //If the lastNodeId is 0, you have to select one at random.
            if (startNodeId == 0)
            {
                //Gets the first node of the route, a random one.
                int startNodeInt = 1 + (int) (Math.random() * nodes.size());
                startNodeId = nodes.get(startNodeInt).getId();
            }

            startNode = getNode(startNodeId);
            routeNodes.add(startNode);

            int nodeIndex = 0;
            //Checks for each of the ways...
            for (Way w : remainingWays)
            {
                //its nodes, to see which way the startnode belongs to.
                for (WayNode wn : w.getWayNodes())
                {
                    if (wn.getNodeId() == startNode.getId())
                    {
                        currentWay = w;
                        nodeIndex = w.getWayNodes().indexOf(wn);
                        break;
                    }
                }
            }

            //Start counting from index and continue on the way
            for (int i = nodeIndex; i < currentWay.getWayNodes().size(); i++)
            {
                for (Node n : nodes)
                {
                    long nodeId = currentWay.getWayNodes().get(i).getNodeId();
                    if (n.getId() == nodeId)
                    {
                        if (i == currentWay.getWayNodes().size() - 1)
                        {
                            startNodeId = n.getId();
                        } else
                        {
                            if (!routeNodes.contains(n))
                            {
                                routeNodes.add(n);
                            }
                        }

                        break;
                    }
                }
            }
            remainingWays.remove(currentWay);
        }
        //Now you have the currentway, find the node IN the waynodes.
        return routeNodes;
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

                } else
                {
                    if (entity instanceof Way)
                    {
                        Way way = (Way) entity;
                        ways.add(way);
                    } else
                    {
                        if (entity instanceof Relation)
                        {
                            Relation relation = (Relation) entity;
                            relations.add(relation);
                        }
                    }
                }
            }

            public void release()
            {
            }

            public void complete()
            {
            }

            @Override
            public void initialize(Map<String, Object> map)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        //boolean pbf = false;
        CompressionMethod compression = CompressionMethod.None;

//        if (file.getName().endsWith(".pbf"))
//        {
//            pbf = true;
//        } else
//        {
//            if (file.getName().endsWith(".gz"))
//            {
//                compression = CompressionMethod.GZip;
//            } else
//            {
//                if (file.getName().endsWith(".bz2"))
//                {
//                    compression = CompressionMethod.BZip2;
//                }
//            }
//        }

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
            } catch (InterruptedException e)
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
