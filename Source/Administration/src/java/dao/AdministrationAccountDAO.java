package dao;

import domain.AdministrationAccount;
import java.util.List;

public interface AdministrationAccountDAO {
    
    int size();

    void insert(AdministrationAccount administrationAccount);

    AdministrationAccount update(AdministrationAccount administrationAccount);

    void delete(AdministrationAccount administrationAccount);

    List<AdministrationAccount> findAll();

    AdministrationAccount find(String username);
}
