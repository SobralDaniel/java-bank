package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.Account;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends AbstractModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // make sure to remove accounts if unlinked from customer
            orphanRemoval = true,

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "customer",

            // fetch accounts from database together with user
            fetch = FetchType.EAGER
    )
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + firstName + lastName +"email= "+ email + "phone= " + phone + '\'' +
                ", accounts=" + accounts +
                "} " + super.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


