package org.academiadecodigo.javabank.domain;

public abstract class Account {


    private double balance = 0;
    private int id;

    public Account(int id) {
        this.id = id;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public boolean debit(double amount) {
            balance -= amount;
            return true;
    }

    public double getBalance() {
        return balance;
    }


    public int getId() {
        return id;
    }
}
