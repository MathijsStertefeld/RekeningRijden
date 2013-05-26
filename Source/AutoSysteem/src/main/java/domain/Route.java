/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.marbl.autosysteem.GeoPosition;
import java.util.ArrayList;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class Route
{

    private int targetNodeIndex;
    private ArrayList<Node> route;

    public Route(ArrayList<Node> route)
    {
        this.route = route;
        this.targetNodeIndex = 0;
    }

    public int getTargetIndex()
    {
        return targetNodeIndex;
    }
    
    public void increaseTargetIndex()
    {
        if (targetNodeIndex < this.route.size() - 1)
        {
            this.targetNodeIndex++;
        }
    }

    public ArrayList<Node> getRoute()
    {
        return route;
    }

    public void setRoute(ArrayList<Node> route)
    {
        this.route = route;
    }

    public GeoPosition getTargetNodePosition()
    {
        return new GeoPosition(route.get(targetNodeIndex).getLatitude(), route.get(targetNodeIndex).getLongitude());
    }

    public GeoPosition getNodePositionFromIndex(int i)
    {
        return new GeoPosition(route.get(i).getLatitude(), route.get(i).getLongitude());
    }
}
