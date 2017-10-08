package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customer {

    private int customerID;
    private AccountManager accountManager;
    private Set<Integer> noaccounts = new HashSet<>();
    private Map<Integer, Account> accounts = new HashMap<>();

    public Customer(int id){
        this.customerID=id;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public int openAccount(AccountType accountType) {
        Account account = accountManager.openAccount(accountType);
        accounts.put(account.getId(), account);
        return account.getId();
    }

    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

     public int getCustomerID(){
        return this.customerID;
     }

     public Set getAccounts(){
         if(accounts.isEmpty())
            return noaccounts;
         return accounts.keySet();

     }

}
