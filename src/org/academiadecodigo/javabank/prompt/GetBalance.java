package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;

public class GetBalance implements Execute{
    AccountManager accountManager;
    Bank bank;

    GetBalance(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    @Override
    public void execute() {
        Menu menu = new Menu(bank, accountManager);
        System.out.println("Your Balance: " + bank.getActive().getBalance(menu.srcAcc()));
    }
}
