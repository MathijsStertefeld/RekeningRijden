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

// This service calls the backend's service to manipulate entities.

@Stateless
public class RekeningRijdersService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private WebResource wr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    //<editor-fold defaultstate="collapsed" desc="Bill Methods">
    public Bill editBill(Bill bill) {
        ClientResponse cr = wr.path("bills")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, bill);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Bill.class);
            default:
                return null;
        }
    }

    public Bill findBill(Long id) {
        ClientResponse cr = wr.path("bills").path(id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Bill.class);
            default:
                return null;
        }
    }

    public ArrayList<Bill> findBillsByBSN(Integer driverBSN) {
        ClientResponse cr = wr.path("bills")
                .queryParam("driverBSN", driverBSN.toString())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        GenericType<ArrayList<Bill>> gt = new GenericType<ArrayList<Bill>>() {
        };

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(gt);
            default:
                return null;
        }
    }

    public Bill payBill(Long billId) {
        Bill bill = findBill(billId);
        bill.setPaymentDate(new Date());
        bill.setPaymentStatus(PaymentStatus.PAID);
        return editBill(bill);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Car Methods">
    public Car editCar(Car car) {
        ClientResponse cr = wr.path("cars")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, car);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Car.class);
            default:
                return car;
        }
    }

    public Car findCar(String carTrackerId) {
        ClientResponse cr = wr.path("cars").path(carTrackerId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Car.class);
            default:
                return null;
        }
    }

    public ArrayList<Car> findCarsByBSN(Integer driverBSN) {
        ClientResponse cr = wr.path("cars")
                .queryParam("driverBSN", driverBSN.toString())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        GenericType<ArrayList<Car>> gt = new GenericType<ArrayList<Car>>() {
        };

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(gt);
            default:
                return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Driver Methods">
    public void createDriver(Driver driver) {
        ClientResponse cr = wr.path("drivers")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, driver);
    }

    public Driver editDriver(Driver driver) {
        ClientResponse cr = wr.path("drivers")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, driver);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Driver.class);
            default:
                return null;
        }
    }

    public Driver findDriver(Integer bsn) {
        ClientResponse cr = wr.path("drivers").path(bsn.toString())
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Driver.class);
            default:
                return null;
        }
    }

    public Driver login(String email, String password) {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");
        password = hasher.hash(password);

        ClientResponse cr = wr.path("drivers").path("login")
                .queryParam("email", email)
                .queryParam("password", password)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Driver.class);
            default:
                return null;
        }
    }
    //</editor-fold>
    //</editor-fold>
}
