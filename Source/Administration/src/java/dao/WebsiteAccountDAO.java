package dao;

import domain.WebsiteAccount;
import java.util.List;

public interface WebsiteAccountDAO {
    
    int size();

    void insert(WebsiteAccount websiteAccount);

    WebsiteAccount update(WebsiteAccount websiteAccount);

    void delete(WebsiteAccount websiteAccount);

    List<WebsiteAccount> findAll();

    WebsiteAccount find(Integer bsn);
}
