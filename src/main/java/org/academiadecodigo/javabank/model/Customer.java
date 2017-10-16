package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Customer extends AbstractModel {

    private String name;
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

}


