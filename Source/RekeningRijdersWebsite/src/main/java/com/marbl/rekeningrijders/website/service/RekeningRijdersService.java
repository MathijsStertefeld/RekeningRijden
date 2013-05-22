package com.marbl.rekeningrijders.website.service;

import com.marbl.administration.domain.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class RekeningRijdersService implements Serializable {

    private WebResource service;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/AdministrationBackend/");
    }

    public void payBill(Long billID) {
        Bill bill = findBill(billID);
        bill.pay();
        service.path("resources").path("bill").put(Bill.class, bill);
    }

    public void editDriver(Driver driver) {
        service.path("resources").path("driver").put(Driver.class, driver);
    }

    public Driver findDriver(int bsn) {
        Driver driver = service.path("resources").path("driver").path(Integer.toString(bsn))
                .accept(MediaType.APPLICATION_JSON).get(Driver.class);
        return driver;
    }

    public Driver findDriverByEmail(String email) {
        Driver driver = service.path("resources").path("driver").path("email").path(email)
                .accept(MediaType.APPLICATION_JSON).get(Driver.class);

        return driver;
    }

    public void register(Driver driver) {
        service.path("resources").path("driver").post(Driver.class, driver);
    }

    public Collection<Bill> findBillsByBsn(int bsn) {
        Driver driver = findDriver(bsn);
        return driver.getBills();
    }

    public Bill findBill(Long billId) {
        Bill bill = service.path("resources").path("bill").path(Long.toString(billId))
                .accept(MediaType.APPLICATION_JSON).get(Bill.class);

        return bill;
    }

    public void editBill(Bill bill) {
        service.path("resources").path("bill").accept(MediaType.APPLICATION_JSON).put(bill);
    }

    public Collection<Car> getCarsFromDriver(int bsn) {
        Driver driver = findDriver(bsn);
        return driver.getCars();
    }

    public Car findCar(String licensePlate) {
        Car car = service.path("resources").path("car").path(licensePlate)
                .accept(MediaType.APPLICATION_JSON).get(Car.class);
        return car;
    }

    public void editCar(Car car) {
        service.path("resources").path("car").put(Car.class, car);
    }
    
    public String hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bi = new BigInteger(1, digest);
            text = String.format("%0" + (digest.length << 1) + "X", bi);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RekeningRijdersService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RekeningRijdersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return text;
    }
}
