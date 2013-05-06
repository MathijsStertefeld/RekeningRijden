package administratiewebsite.service;

import administratiewebsite.domain.Employee;
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
        webResource = client.resource("http://localhost:8080/Administration/");
    }

    public void create(Employee employee) {
        webResource.path("resources").path("employee").post(Employee.class, employee);
    }

    public Employee edit(Employee employee) {
        return webResource.path("resources").path("employee").put(Employee.class, employee);
    }

    public void remove(String username) {
        webResource.path("resources").path("employee").path(username).delete();
    }

    public Employee find(String username) {
        return webResource.path("resources").path("employee").path(username).get(Employee.class);
    }

    public Collection<Employee> findAll() {
        return webResource.path("resources").path("employee").get(new GenericType<Collection<Employee>>() { });
    }

    public Collection<Employee> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("employee").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Employee>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("employee").path("count").get(String.class));
    }
}
