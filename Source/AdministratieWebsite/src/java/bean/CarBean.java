package bean;

import administration.domain.Car;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class CarBean {

    private WebResource service;
    private Car currentCar;
    private Collection<Car> cars;

    public void setCurrentCar(Car car) {
        this.currentCar = car;
    }

    public Car getCurrentCar() {
        return currentCar;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/Kwetter/");

        cars = new ArrayList<Car>(service.path("resources").path("cars")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Car>>() {
        }));
    }

    public void create() {
        service.path("resources").path("cars")
                .accept(MediaType.APPLICATION_JSON).post(currentCar);
    }

    public void edit() {
        service.path("resources").path("cars")
                .accept(MediaType.APPLICATION_JSON).put(currentCar);
    }
}
