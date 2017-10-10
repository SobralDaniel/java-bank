package org.academiadecodigo.javabank.model.account;

public abstract class AbstractAccount implements Account {

    private int id;
    private double balance = 0;

    public AbstractAccount(int id) {
        this.id = id;
    }

    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public abstract AccountType getAccountType();

    public int getId() {
        return id;
    }

    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    public boolean canCredit(double amount) {
        return amount > 0;
    }
}
