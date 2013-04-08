package bean;

import administration.domain.CityRate;
import administration.domain.HighwayRate;
import administration.domain.RegionRate;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RateBean {

    /**
     * Creates a new instance of RateBean
     */
    public RateBean() {
    }
    
    public CityRate getCityRate()
    {
        return null;
    }
    
    public HighwayRate getHighwayRate()
    {
        return null;
    }
    
    public RegionRate getRegionRate()
    {
        return null;
    }
    
    public void setRate()
    {
        
    }
    
    public void save()
    {
        
    }
    
    public void edit()
    {
        
    }
}
