package bean;

import administration.domain.Bill;
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
public class BillBean implements Serializable {

    WebResource service;
    private Bill currentBill;
    private Collection<Bill> bills;

    public void setCurrentBill(Bill bill) {
        this.currentBill = bill;
    }

    public Bill getCurrentBill() {
        return currentBill;
    }

    public Collection<Bill> getBills() {
        return bills;
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/Kwetter/");

        bills = new ArrayList<Bill>(service.path("resources").path("bills")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Bill>>() {
        }));
    }

    public void create() {
        service.path("resources").path("bills").path("follow")
                .accept(MediaType.APPLICATION_JSON).post(currentBill);
    }

    public void edit() {
        service.path("resources").path("bills").path("follow")
                .accept(MediaType.APPLICATION_JSON).put(currentBill);
    }
}
