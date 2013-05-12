/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import verplaatsingensysteem.domain.VehiclePosition;
import verplaatsingensysteem.service.VerplaatsingSysteemService;

/**
 *
 * @author Leslie Aerts
 */
@Named(value = "receiverBean")
@Dependent
@Stateless
public class ReceiverBean implements Serializable
{
    
    private VerplaatsingSysteemService vpService;
    /**
     * Creates a new instance of ReceiverBean
     */
    
    List<VehiclePosition> positions;

    public List<VehiclePosition> getPositions()
    {
        return positions;
    }

    public void setPositions(List<VehiclePosition> positions)
    {
        this.positions = vpService.getVehiclePositions("t1");
    }
    
    public ReceiverBean()
    {
        vpService = new VerplaatsingSysteemService();
    }
}
