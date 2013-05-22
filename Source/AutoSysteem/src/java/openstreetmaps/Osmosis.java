/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openstreetmaps;

import java.io.File;
import java.util.ArrayList;
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

    private static List<Node> nodes;

    public static List<Node> getNodes()
    {
        nodes = new ArrayList<Node>();
        parseFile(new File("bigroads.xml"));
        return nodes;
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
                    //System.out.println("Node is " + node.getLongitude() + " / " + node.getLatitude());
                } else
                {
                    if (entity instanceof Way)
                    {
                        Way way = (Way) entity;

//                    List<WayNode> wayNodes;
//                        wayNodes = way.getWayNodes();

                        if (way.getId() == 4248627)
                        {
                            System.out.println("WAY = " + way.getId());
                            for (WayNode wn : way.getWayNodes())
                            {
                                System.out.println("WN " + wn.getNodeId());
                            }
                        }



                        System.out.println("way");
                    } else
                    {
                        if (entity instanceof Relation)
                        {
                            System.out.println("relation");
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
}
