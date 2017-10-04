package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.SavingAccount;

public class SavingAccountTest {

    public boolean test(){
        SavingAccount account = new SavingAccount(1);

        // account should start with zero money
        if (account.getBalance() != 0) {
            return false;
        }

        // we should be able to deposit money in account
        account.credit(120);
        if (account.getBalance() != 120) {
            return false;
        }

        // we should be able to take money from account
        account.debit(20);
        if (account.getBalance() != 100) {
            return false;
        }

        // customer must keep a min balance on savings account
        account.debit(20);
        if (account.getBalance() != 100){
            return false;
        }

        return true;
    }
}
