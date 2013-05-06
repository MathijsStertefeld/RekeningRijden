package bean;

import administration.domain.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

@Named
@SessionScoped
public class CarBean implements Serializable {

    private WebResource service;
    private Car car;
    private Collection<Car> cars;

    public Collection<Car> getCars() {
        return cars;
    }
    
    public String getCarTrackerId() {
        if (car != null) {
            return car.getCarTrackerId();
        } else {
            return "";
        }
    }
    
    public void setCarTrackerId(String carTrackerId) {
        car = service.path("resources").path("car").path(carTrackerId)
                .accept(MediaType.APPLICATION_JSON)
                .get(Car.class);
    }
    
    public int getDriverBsn() {
        if (car != null) {
            return car.getDriverBsn();
        } else {
            return 0;
        }
    }
    
    public String getLicensePlate() {
        if (car != null) {
            return car.getLicensePlate();
        } else {
            return "";
        }
    }
    
    public String getBrand() {
        if (car != null) {
            return car.getBrand();
        } else {
            return "";
        }
    }
    
    public String getModel() {
        if (car != null) {
            return car.getModel();
        } else {
            return "";
        }
    }
    
    public CarType getType() {
        if (car != null) {
            return car.getType();
        } else {
            return CarType.UNKNOWN;
        }
    }
    
    public PaintColor getPaintColor() {
        if (car != null) {
            return car.getPaintColor();
        } else {
            return PaintColor.UNKNOWN;
        }
    }
    
    public void setPaintColor(PaintColor paintColor) {
        if (car != null) {
            car.setPaintColor(paintColor);
        }
    }
    
    public int getMass() {
        if (car != null) {
            return car.getMass();
        } else {
            return 0;
        }
    }
    
    public void setMass(int mass) {
        if (car != null) {
            car.setMass(mass);
        }
    }
    
    public Classification getClassification() {
        if (car != null) {
            return car.getClassification();
        } else {
            return Classification.UNKNOWN;
        }
    }
    
    public void setClassification(Classification classification) {
        if (car != null) {
            car.setClassification(classification);
        }
    }

    public void create() {
        service.path("resources").path("car")
                .accept(MediaType.APPLICATION_JSON).post(car);
    }

    public void edit() {
        service.path("resources").path("car")
                .accept(MediaType.APPLICATION_JSON).put(car);
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/Administration/");

        cars = new ArrayList<Car>(service.path("resources").path("car")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Car>>() {
        }));
    }
}
