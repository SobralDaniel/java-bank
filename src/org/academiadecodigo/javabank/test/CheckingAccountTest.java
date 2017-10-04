package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.CheckingAccount;

public class CheckingAccountTest {

    public boolean test() {

        CheckingAccount account = new CheckingAccount(1);

        // account should start with zero money
        if (account.getBalance() != 0) {
            return false;
        }

        // we should be able to deposit money in account
        account.credit(80);
        if (account.getBalance() != 80) {
            return false;
        }

        // we should be able to take money from account
        account.debit(40);
        if (account.getBalance() != 40) {
            return false;
        }

        account.debit(50);
        if (account.getBalance() != 40){
            return false;
        }

        return true;
    }
}
