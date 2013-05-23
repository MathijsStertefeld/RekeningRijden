/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import java.util.ArrayList;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class Route
{

    private int progress;
    private ArrayList<Node> route;

    public Route(ArrayList<Node> route)
    {
        this.route = route;
        this.progress = 0;
    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
    }

    public ArrayList<Node> getRoute()
    {
        return route;
    }

    public void setRoute(ArrayList<Node> route)
    {
        this.route = route;
    }
    
    
}
