package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;


public class OpenAcc implements Execute{
    AccountManager accountManager;
    Bank bank;

    OpenAcc(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    @Override
    public void execute() {
        Menu menu = new Menu(bank, accountManager);
        bank.getActive().openAccount(menu.accountType());
    }
}
