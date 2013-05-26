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

    private WebResource wr;
    private GenericType<ArrayList<Bill>> billArrayList;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/bills/");
        billArrayList = new GenericType<ArrayList<Bill>>() {
        };
    }

    public void create(Bill bill) {
        ClientResponse cr = wr
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, bill);
    }

    public Bill edit(Bill bill) {
        ClientResponse cr = wr
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, bill);

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Bill.class);
            default:
                return null;
        }
    }

    public Bill find(Long id) {
        return wr.path(id.toString()).accept(MediaType.APPLICATION_JSON)
                .get(Bill.class);
    }

    public ArrayList<Bill> findAll() {
        return wr.accept(MediaType.APPLICATION_JSON).get(billArrayList);
    }
}
