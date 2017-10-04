package org.academiadecodigo.javabank.domain;

public class SavingAccount extends Account {

    public static final int MIN_SAVINGS_BALANCE = 100;

    public SavingAccount (int id){
        super(id);
    }

    @Override
    public boolean debit(double amount) {
        if(super.getBalance()<(amount + MIN_SAVINGS_BALANCE)) {
            return false;
        }
        super.debit(amount);
        return true;
    }
}
