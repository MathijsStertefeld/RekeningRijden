package com.marbl.administration.website.service;

import com.marbl.administration.domain.Bill;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class BillService implements Serializable {

    private WebResource resource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        resource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/bills/");
    }
    
    public void create(Bill bill) {
        resource.post(Bill.class, bill);
    }

    public Bill edit(Bill bill) {
        return resource.accept(MediaType.APPLICATION_JSON).put(Bill.class, bill);
    }

    public Bill find(Long id) {
        return resource.path(id.toString()).accept(MediaType.APPLICATION_JSON).get(Bill.class);
    }

    public ArrayList<Bill> findAll() {
        return resource.accept(MediaType.APPLICATION_JSON).get(new GenericType<ArrayList<Bill>>() { });
    }
}
