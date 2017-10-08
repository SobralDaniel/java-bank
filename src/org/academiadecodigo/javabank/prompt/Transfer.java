package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;


public class Transfer implements Execute{
    AccountManager accountManager;
    Bank bank;

    Transfer(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    @Override
    public void execute() {
        Menu menu = new Menu(bank, accountManager);
        accountManager.transfer(menu.srcAcc(), menu.dstAcc(), menu.amount());
    }
}
