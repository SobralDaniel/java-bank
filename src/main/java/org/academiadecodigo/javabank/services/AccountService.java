package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

public interface AccountService {

    void add(Account account, Customer customer);

    void deposit(int id, double amount);

    void withdraw(int id, double amount);

    void transfer(int srcId, int dstId, double amount);

}
