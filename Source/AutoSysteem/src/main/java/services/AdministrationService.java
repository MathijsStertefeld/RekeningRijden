/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.marbl.administration.domain.Car;
import com.marbl.administration.domain.Driver;
import com.marbl.administration.domain.utils.Hasher;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.util.Collection;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Eagle
 */
public class AdministrationService {

    private WebResource wr;

    public AdministrationService() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);

        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public Collection<Car> getCarsFromUser(Driver driver) {
        Collection<Car> cars;
        
        String bsn = Integer.toString(driver.getBSN());

        cars = wr.path("cars")
                .queryParam("driverBSN", bsn)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Car>>() {
        });

        return cars;
    }

    public Driver getDriver(String email, String password) {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");
        password = hasher.hash(password);

        System.out.println();
        ClientResponse cr = wr.path("drivers").path("login")
                .queryParam("email", email)
                .queryParam("password", password)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Driver.class);
            default:
                return null;
        }
    }
}
