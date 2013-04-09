package bean;

import administration.domain.Rate;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class RateBean implements Serializable {

    WebResource service;
    private Rate currentRate;
    private Collection<Rate> rates;

    public void setCurrentRate(Rate rate) {
        this.currentRate = rate;
    }

    public Rate getCurrentRate() {
        return currentRate;
    }

    public Collection<Rate> getRates() {
        return rates;
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/Kwetter/");

        rates = new ArrayList<Rate>(service.path("resources").path("rates")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Rate>>() {
        }));
    }

    public void create() {
        service.path("resources").path("rates")
                .accept(MediaType.APPLICATION_JSON).post(currentRate);
    }

    public void edit() {
        service.path("resources").path("rates")
                .accept(MediaType.APPLICATION_JSON).put(currentRate);
    }
}
