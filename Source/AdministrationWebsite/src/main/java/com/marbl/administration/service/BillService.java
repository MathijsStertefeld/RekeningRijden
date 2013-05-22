package com.marbl.administration.service;

import com.marbl.administration.domain.Bill;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class BillService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://localhost:8080/AdministrationService/");
    }

    public void create(Bill bill) {
        webResource.path("resources").path("bill").post(Bill.class, bill);
    }

    public Bill edit(Bill bill) {
        return webResource.path("resources").path("bill").put(Bill.class, bill);
    }

    public void remove(Long id) {
        webResource.path("resources").path("bill").path(id.toString()).delete();
    }

    public Bill find(Long id) {
        return webResource.path("resources").path("bill").path(id.toString()).get(Bill.class);
    }

    public Collection<Bill> findAll() {
        return webResource.path("resources").path("bill").get(new GenericType<Collection<Bill>>() { });
    }

    public Collection<Bill> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("bill").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Bill>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("bill").path("count").get(String.class));
    }
}
