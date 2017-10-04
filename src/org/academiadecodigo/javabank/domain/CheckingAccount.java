package org.academiadecodigo.javabank.domain;

public class CheckingAccount extends Account {

    public CheckingAccount (int id){
        super(id);
    }

    @Override
    public boolean debit(double amount) {
        if (super.getBalance()>= amount)
            return super.debit(amount);
        return false;
    }
}
