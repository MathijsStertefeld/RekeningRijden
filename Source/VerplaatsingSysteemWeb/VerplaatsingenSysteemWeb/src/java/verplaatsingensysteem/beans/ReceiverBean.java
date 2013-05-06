/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.beans;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
    public ReceiverBean()
    {
        vpService = new VerplaatsingSysteemService();
    }
}
