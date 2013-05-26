package com.marbl.administration.website.service;

import com.marbl.administration.domain.Employee;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class EmployeeService implements Serializable {

    private WebResource wr;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/employees/");
    }

    public Employee find(String username) {
        return wr.path(username)
                .get(Employee.class);
    }
    
    public Employee login(String username, String password) {
        ClientResponse cr = wr.path("login")
                .queryParam("username", username)
                .queryParam("password", password)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Employee.class);
            default:
                return null;
        }
    }
}
