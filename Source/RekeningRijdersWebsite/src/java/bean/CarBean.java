package bean;

import administration.domain.*;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.RekeningRijdersService;

@Named
@SessionScoped
public class CarBean implements Serializable {

    private String licensePlate;
    private Car car;
    private Collection<Car> cars;
    
    private int bsn;
    
    @Inject
    private RekeningRijdersService service;

    public int getBsn(){
        return bsn;
    }
    
    public void setBsn(int i) {
        bsn = i;
        cars = service.getCarsFromDriver(bsn);
    }
    
    public String getLicensePlate(){
        return licensePlate;
    }
    
    public Car getCar()
    {
        return car;
    }
    
    public void setLicensePlate(String s){
        licensePlate = s;
        for(Car c : cars)
        {
            if(c.getLicensePlate().equals(licensePlate))
            {
                car = c;
            }
        }
    }
    
    public Collection<Car> getCars() {
        return cars;
    }
        
    public void setPaintColor(int paintColor) {
        if(car != null)
        {
            car.setPaintColor(PaintColor.values()[paintColor]);
        }
        
        service.editCar(car);
    }
    
    public void edit()
    {
        
    }
}
