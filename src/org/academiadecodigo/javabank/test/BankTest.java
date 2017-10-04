package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.*;

public class BankTest {

    public boolean test() {

        Bank bank = new Bank();

        // bank initial balance should be 0
        if (bank.getBalance() != 0) {
            return false;
        }

        Account a1 = new CheckingAccount(1);
        Account a2 = new SavingAccount(2);
        Account a3 = new CheckingAccount(3);
        Account a4 = new SavingAccount(4);

        a1.credit(10);
        a2.credit(20);
        a3.credit(30);
        a4.credit(40);

        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.addAccount(a1);
        c1.addAccount(a2);
        c2.addAccount(a3);
        c2.addAccount(a4);

        bank.addCustomer(c1);
        bank.addCustomer(c2);

        // bank balance should equal sum of all accounts
        if (bank.getBalance() != 100) {
            return false;
        }

        return true;
    }
}
