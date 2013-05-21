package administratiewebsite.service;

import java.io.Serializable;
import javax.ejb.Stateless;

@Stateless
public abstract class AbstractService<Entity extends Serializable, ID extends Serializable> implements Serializable {
 
}
