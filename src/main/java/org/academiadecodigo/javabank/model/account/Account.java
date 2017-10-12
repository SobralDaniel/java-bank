package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.model.Model;

public interface Account extends Model {

    AccountType getAccountType();

    double getBalance();

    void credit(double amount);

    void debit(double amount);

    boolean canDebit(double amount);

    boolean canCredit(double amount);

}
