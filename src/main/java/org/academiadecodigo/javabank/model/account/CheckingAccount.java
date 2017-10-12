package org.academiadecodigo.javabank.model.account;

public class CheckingAccount extends AbstractAccount {

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
