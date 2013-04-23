package bean;

import administration.domain.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

@Named
@SessionScoped
public class RateBean implements Serializable {

    private transient WebResource service;
    private Rate rate;
    private Collection<Rate> rates;

    public Collection<Rate> getRates() {
        return rates;
    }
    
    public String getName() {
        if (rate != null) {
            return rate.getName();
        }
        return "";
    }
    
    public void setName(String name) {
        rate = service.path("resources").path("rate").path(name)
                .accept(MediaType.APPLICATION_JSON)
                .get(Rate.class);
    }
    
    public double getPrice() {
        if (rate != null) {
            return rate.getPrice();
        } else {
            return 0;
        }
    }
    
    public void setPrice(double price) {
        if (rate != null) {
            rate.setPrice(price);
        }
    }

    public void create() {
        service.path("resources").path("rate")
                .accept(MediaType.APPLICATION_JSON).post(rate);
    }

    public void edit() {
        service.path("resources").path("rate")
                .accept(MediaType.APPLICATION_JSON).put(rate);
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/Administration/");

        rates = new ArrayList<Rate>(service.path("resources").path("rate")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Rate>>() {
        }));
    }
}
