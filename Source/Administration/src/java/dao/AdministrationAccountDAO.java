package dao;

import domain.AdministrationAccount;
import java.util.List;

public interface AdministrationAccountDAO {
    
    void persist(AdministrationAccount administrationAccount);

    AdministrationAccount merge(AdministrationAccount administrationAccount);

    void remove(AdministrationAccount administrationAccount);

    List<AdministrationAccount> findAll();

    AdministrationAccount find(String username);
}
