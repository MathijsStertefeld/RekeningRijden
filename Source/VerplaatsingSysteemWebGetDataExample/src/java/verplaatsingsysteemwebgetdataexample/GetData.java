/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingsysteemwebgetdataexample;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.MediaType;
import verplaatsingensysteem.domain.VehiclePosition;

/**
 *
 * @author Eagle
 */
public class GetData
{

    private WebResource resource;

    public GetData()
    {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        resource = client.resource("http://localhost:8080/VerplaatsingSysteemWeb");
    }
    ///resources/getInfo/getVehiclePosition/t0

    public List<VehiclePosition> getVehiclePosition(String carTrackerId)
    {
        List<VehiclePosition> positions = (List<VehiclePosition>)
                resource.path("resources").path("getInfo").path("getVehiclePosition").path(carTrackerId).accept(MediaType.APPLICATION_JSON).get(new GenericType<Collection<VehiclePosition>>()
                {
                });
        return positions;
    }
}
