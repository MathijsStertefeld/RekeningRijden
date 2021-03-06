package com.marbl.administration.website.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Employee;
import com.marbl.administration.domain.utils.Hasher;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
//</editor-fold>

// This service calls the backend's service to manipulate employees.

@Stateless
public class EmployeeService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private WebResource wr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/employees/");
    }

    public Employee find(String username) {
        ClientResponse cr = wr.path(username)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Employee.class);
            default:
                return null;
        }
    }
    
    public Employee login(String username, String password) {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");
        password = hasher.hash(password);
        
        ClientResponse cr = wr.path("login")
                .queryParam("username", username)
                .queryParam("password", password)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class);
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Employee.class);
            default:
                return null;
        }
    }
    //</editor-fold>
}
