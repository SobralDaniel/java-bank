package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.Set;

public class Withdraw implements Execute{
    AccountManager accountManager;
    Bank bank;

    Withdraw(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    @Override
    public void execute() {
        Menu menu = new Menu(bank, accountManager);
        accountManager.withdraw(menu.srcAcc(),menu.amount());
    }
}
