/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.marbl.administration.domain.Car;
import com.marbl.administration.domain.Driver;
import com.marbl.administration.domain.utils.Hasher;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.MediaType;
import sun.security.provider.SHA;

/**
 *
 * @author Eagle
 */
public class AdministrationService
{

    private WebResource resource;

    public AdministrationService()
    {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        resource = client.resource("http://192.168.30.185:8080/AdministrationBackend/");
    }

    public Collection<Car> getCarsFromUser(Driver driver)
    {
        Collection<Car> cars;
        System.out.println(resource.path("resources").path("cars").queryParam("driverBsn", Integer.toString(driver.getBsn())));
        
        String bsn =Integer.toString(driver.getBsn());
        
        cars = resource.path("resources").path("cars")
                .queryParam("driverBsn", bsn )
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Car>>() {
        });
//.path("resources").path("cars").queryParam("driverBsn", Integer.toString(driver.getBsn())).accept(MediaType.APPLICATION_JSON).get(new GenericType<Collection<Car>>()
 //       {
   //     });

//        Collection<String> carTrackers = new ArrayList<String>();
//
//        for (int i = 0; i < cars.size(); i++)
//        {
//            carTrackers.add(cars.get(i).getCarTrackerId());
//        }

        return cars;
    }

    public Driver getDriver(String email, String password)
    {
        Driver driver;

        Hasher hasher = new Hasher("SHA-256", "UTF-8");

        driver = new ArrayList<Driver>(resource.path("resources").path("drivers").queryParam("email", email).accept(MediaType.APPLICATION_JSON).get(new GenericType<Collection<Driver>>()
        {
        })).get(0);

        String hashedPassword = hasher.hash(password);

        if (hashedPassword.equals(driver.getPassword()))
        {
            return driver;
        }
        else
        {
            return null;
        }
    }
}
