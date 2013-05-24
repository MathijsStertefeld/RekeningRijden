package com.marbl.administration.website.service;

import com.marbl.administration.domain.Bill;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class BillService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public Bill edit(Bill bill) {
        return webResource.path("bills").accept(MediaType.APPLICATION_JSON).put(Bill.class, bill);
    }

    public Bill find(Long id) {
        return webResource.path("bills").path(id.toString()).accept(MediaType.APPLICATION_JSON).get(Bill.class);
    }

    public Collection<Bill> findAll() {
        return webResource.path("bills").accept(MediaType.APPLICATION_JSON).get(new GenericType<Collection<Bill>>() { });
    }
}
