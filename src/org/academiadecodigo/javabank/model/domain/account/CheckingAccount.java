package org.academiadecodigo.javabank.model.domain.account;

public class CheckingAccount extends AbstractAccount {

    public CheckingAccount(int id) {
        super(id);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }

}
