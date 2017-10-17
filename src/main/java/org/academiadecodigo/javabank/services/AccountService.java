package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;

public interface AccountService {

    void deposit(Integer id, double amount);

    void withdraw(Integer id, double amount);

    void transfer(Integer srcId, Integer dstId, double amount);

    Account addAccount (Account account);

}
