package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
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
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);



        Prompt prompt = new Prompt(System.in, System.out);
        IntegerSetInputScanner scanner1 = new IntegerSetInputScanner(bank.getCostumersID());
        scanner1.setMessage("Insert your customer number: ");
        scanner1.setError("That is not a valid account!");
        bank.setActive(prompt.getUserInput(scanner1));

        String[] options = {"Deposit", "Withdraw", "Transfer", "Open Account", "Quit"};
        MenuInputScanner scanner2 = new MenuInputScanner(options);
        scanner2.setMessage("Choose an option: ");

        int option = prompt.getUserInput(scanner2);
        System.out.println(option);
        switch(option){
            case 1: Deposit.execute();
                    break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: return;
            default:
        }
    }
}
