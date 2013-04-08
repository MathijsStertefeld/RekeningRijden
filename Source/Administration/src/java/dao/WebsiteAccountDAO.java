package dao;

import domain.WebsiteAccount;
import java.util.List;

public interface WebsiteAccountDAO {
    
    void persist(WebsiteAccount websiteAccount);

    WebsiteAccount merge(WebsiteAccount websiteAccount);

    void remove(WebsiteAccount websiteAccount);

    List<WebsiteAccount> findAll();

    WebsiteAccount find(Integer bsn);
}
