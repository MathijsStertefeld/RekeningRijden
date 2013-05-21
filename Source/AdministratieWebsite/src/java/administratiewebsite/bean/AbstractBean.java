package administratiewebsite.bean;

import administratiewebsite.service.AbstractService;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public abstract class AbstractBean<Entity extends Serializable, ID extends Serializable> implements Serializable {
    
    @Inject
    private AbstractService<Entity, ID> service;
    private Collection<Entity> all;
    private Entity current;
}
