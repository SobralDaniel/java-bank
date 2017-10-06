package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Deposit implements Execute{
    AccountManager accountManager;
    Bank bank;

    Deposit(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    @Override
    public void execute() {
        Prompt prompt = new Prompt(System.in, System.out);
        IntegerSetInputScanner scanner1 = new IntegerSetInputScanner(bank.getActive().getAccounts());
        scanner1.setMessage("Insert your account number: ");
        scanner1.setError("That is not a valid account!");
        bank.setActive(prompt.getUserInput(scanner1));

    }
}
