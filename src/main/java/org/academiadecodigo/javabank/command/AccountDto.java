package org.academiadecodigo.javabank.command;

import org.academiadecodigo.javabank.model.account.AccountType;

public class AccountDto {

    private Integer id;
    private AccountType accountType;
    private double balance = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
