package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.javabank.managers.AccountManager;

public class OpenAcc implements Execute{

    AccountManager accountManager;

    OpenAcc(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void execute(int customerID) {

    }
}
