package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.Set;

public class Bank {

    private AccountManager accountManager;
    private Set<Customer> customers = new HashSet<>();
    private Customer active;

    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setAccountManager(accountManager);
    }

    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public Set getCostumersID() {
        Set<Integer> customersID = new HashSet<>();
        for (Customer customer : customers) {
            customersID.add(customer.getCustomerID());
        }
        return customersID;
    }

    public void setActive(Customer customer){
        this.active = customer;
    }

    public Customer getActive() {
        return active;
    }
}
