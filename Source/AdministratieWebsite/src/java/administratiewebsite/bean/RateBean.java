package administratiewebsite.bean;

import administration.domain.Rate;
import administratiewebsite.service.RateService;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RateBean implements Serializable {

    @Inject
    private RateService rateService;
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
        rate = rateService.find(name);
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

    @PostConstruct
    public void postConstruct() {
        rates = rateService.findAll();
    }

    public void save() {
        rateService.edit(rate);
        rates = rateService.findAll();
    }
}
