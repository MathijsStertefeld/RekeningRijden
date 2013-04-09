package bean;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CarBean{

    private String currentCarTrackerID;

    public void setCurrentCarTrackerID(String currentCarTrackerID) {
        this.currentCarTrackerID = currentCarTrackerID;
    }

    public String getCurrentCarTrackerID() {
        return currentCarTrackerID;
    }
    
    
    public CarBean() {
    }
    
    
    
    public void save()
    {
        
    }
    
    public void edit()
    {
        
    }
}
