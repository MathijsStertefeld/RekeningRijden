package com.marbl.administration.website.service;

import com.marbl.administration.domain.Employee;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class EmployeeService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public Employee find(String username) {
        return webResource.path("employees").path(username).get(Employee.class);
    }
}
