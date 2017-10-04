package org.academiadecodigo.javabank.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    public static final int MIN_SAVINGS_BALANCE = 100;

    private Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
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

    public void deposit(int id, double amount) {
        accounts.get(id).credit(amount);
    }

    public void withdraw(int id, double amount) {

        Account account = accounts.get(id);

        if (account instanceof SavingAccount) {
            return;
        }

        account.debit(amount);

    }

    public void transfer(int srcId, int destId, double amount) {

        Account srcAccount = accounts.get(srcId);
        Account dstAccount = accounts.get(destId);

        /*// if there is no balance in src account do nothing
        if (srcAccount.getBalance() < amount) {
            return;
        }*/


        if(srcAccount.debit(amount))
            dstAccount.credit(amount);

    }

}
