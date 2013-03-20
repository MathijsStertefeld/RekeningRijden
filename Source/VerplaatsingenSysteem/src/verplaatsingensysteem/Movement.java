/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

/**
 *
 * @author Leslie Aerts
 */
class Movement
{

    private int timestep;
    private Lane currentLane;

    public Movement(int timestep, Lane lane)
    {
        this.timestep = timestep;
        this.currentLane = lane;
    }
}
