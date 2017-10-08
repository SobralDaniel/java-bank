package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;


public class PromptInterface {
    public static void main(String[] args){

        AccountManager accountManager = new AccountManager();
        Bank bank = new Bank(accountManager);
        Customer customer1 = new Customer(1);
        customer1.setAccountManager(accountManager);
        Customer customer2 = new Customer(2);
        customer2.setAccountManager(accountManager);
        Customer customer3 = new Customer(13);
        customer3.setAccountManager(accountManager);
        customer1.openAccount(AccountType.CHECKING);
        customer1.openAccount(AccountType.CHECKING);
        customer2.openAccount(AccountType.CHECKING);
        customer2.openAccount(AccountType.CHECKING);
        customer1.openAccount(AccountType.CHECKING);
        customer2.openAccount(AccountType.CHECKING);
        customer3.openAccount(AccountType.CHECKING);
        customer3.openAccount(AccountType.CHECKING);
        customer3.openAccount(AccountType.CHECKING);
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);

        Menu menu = new Menu(bank, accountManager);
        menu.login();
        menu.options();



    }
}
