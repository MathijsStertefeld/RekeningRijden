package com.marbl.rekeningrijders.website.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.*;
import com.marbl.administration.domain.utils.Hasher;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
//</editor-fold>

@Stateless
public class RekeningRijdersService implements Serializable {

    private WebResource wr;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public void payBill(Long billId) {
        Bill bill = findBill(billId);
        bill.setPaymentDate(new Date());
        bill.setPaymentStatus(PaymentStatus.PAID);
        wr.path("bills").put(Bill.class, bill);
    }

    public void editDriver(Driver driver) {
        wr.path("drivers")
                .accept(MediaType.APPLICATION_JSON).put(Driver.class, driver);
    }

    public Driver findDriver(Integer bsn) {
        Driver driver = wr.path("drivers")
                .path(Integer.toString(bsn)).accept(MediaType.APPLICATION_JSON)
                .get(Driver.class);
        return driver;
    }

    public Driver findDriverByEmail(String email) {
        ArrayList<Driver> drivers = new ArrayList(wr
                .path("drivers").queryParam("email", email)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<ArrayList<Driver>>() { }));

        if (drivers.size() > 0) {
            return drivers.get(0);
        } else {
            return null;
        }
    }
    
    public Driver login(String email, String password) {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");
        password = hasher.hash(password);
        
        ClientResponse cr = wr.path("login")
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

    public void register(Driver driver) {
        wr.path("drivers").post(Driver.class, driver);
    }

    public ArrayList<Bill> findBillsByBSN(Integer bsn) {
        return wr.path("bills")
                .queryParam("driverBSN", bsn.toString())
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<ArrayList<Bill>>() {
        });
    }

    public Bill findBill(Long id) {
        Bill bill = wr.path("bills")
                .path(Long.toString(id)).accept(MediaType.APPLICATION_JSON)
                .get(Bill.class);

        return bill;
    }

    public void editBill(Bill bill) {
        wr.path("bills")
                .accept(MediaType.APPLICATION_JSON).put(bill);
    }

    public ArrayList<Car> findCarsByBSN(Integer bsn) {
        return wr.path("cars")
                .queryParam("driverBSN", bsn.toString())
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<ArrayList<Car>>() {
        });
    }

    public Car findCar(String carTrackerId) {
        Car car = wr.path("cars").path(carTrackerId)
                .accept(MediaType.APPLICATION_JSON).get(Car.class);
        return car;
    }

    public void editCar(Car car) {
        wr.path("cars").put(Car.class, car);
    }
}
