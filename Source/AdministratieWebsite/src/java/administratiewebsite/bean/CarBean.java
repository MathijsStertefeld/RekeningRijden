package administratiewebsite.bean;

import administration.domain.Car;
import administration.domain.CarType;
import administration.domain.Classification;
import administration.domain.PaintColor;
import administratiewebsite.service.CarService;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CarBean implements Serializable {

    @Inject
    private CarService carService;
    private Car car;
    private Collection<Car> cars;

    public Collection<Car> getCars() {
        return cars;
    }
    
    public String getLicensePlate() {
        if (car != null) {
            return car.getLicensePlate();
        } else {
            return "";
        }
    }
    
    public void setLicensePlate(String licencePlate) {
        car = carService.find(licencePlate);
    }
    
    public String getCarTrackerId() {
        if (car != null) {
            return car.getCarTrackerId();
        } else {
            return "";
        }
    }
    
    public void setCarTrackerId(String carTrackerId) {
        if (car != null) {
            car.setCarTrackerId(carTrackerId);
        }
    }
    
    public int getDriverBsn() {
        if (car != null) {
            return car.getDriverBsn();
        } else {
            return 0;
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

    @PostConstruct
    public void postConstruct() {
        cars = carService.findAll();
    }

    public void save() {
        carService.edit(car);
        cars = carService.findAll();
    }
}
