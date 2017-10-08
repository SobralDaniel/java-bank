package org.academiadecodigo.javabank.prompt;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;


public class Menu {
    private Bank bank;
    private Prompt prompt;
    private AccountManager accountManager;

    public Menu(Bank bank, AccountManager accountManager){
        this.bank = bank;
        this.accountManager = accountManager;
        this.prompt = new Prompt(System.in, System.out);
    }

    public void login(){
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCostumersID());
        scanner.setMessage("Insert your customer number: ");
        scanner.setError("That is not a valid account!");
        bank.setActive(prompt.getUserInput(scanner));
    }

    public void options(){
        String[] options = {"Deposit", "Withdraw", "Transfer", "Open Account", "Get Balance", "Quit"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Choose an option: ");
        PromptOption promptOption = null;
        int option;

        do {
            option = prompt.getUserInput(scanner);
            switch (option) {
                case 1:
                    promptOption = new PromptOption(new Deposit(accountManager, bank));
                    break;
                case 2:
                    promptOption = new PromptOption(new Withdraw(accountManager, bank));
                    break;
                case 3:
                    promptOption = new PromptOption(new Transfer(accountManager, bank));
                    break;
                case 4:
                    promptOption = new PromptOption(new OpenAcc(accountManager, bank));
                    break;
                case 5:
                    promptOption = new PromptOption(new GetBalance(accountManager, bank));
                    break;
                case 6: return;
                default:
            }
            promptOption.executeOption();
        } while (option != 6);

    }

    public int srcAcc(){
        System.out.println(bank.getActive().getAccounts().toString());
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getActive().getAccounts());
        scanner.setMessage("Insert your source account number: ");
        scanner.setError("That is not a valid account!");
        return prompt.getUserInput(scanner);
    }

    public int dstAcc(){
        System.out.println(bank.getActive().getAccounts().toString());
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getActive().getAccounts());
        scanner.setMessage("Insert you destiny account number: ");
        scanner.setError("That is not a valid account!");
        return prompt.getUserInput(scanner);
    }

    public double amount (){
        IntegerInputScanner scanner = new IntegerInputScanner();
        scanner.setMessage("Insert the amount: ");
        scanner.setError("That is not a valid amount!");
        return prompt.getUserInput(scanner);
    }

    public AccountType accountType(){
        String[] options = {"Checking", "Savings"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Choose an account type: ");
        int option = prompt.getUserInput(scanner);
        switch (option){
            case 1: return AccountType.CHECKING;
            case 2: return AccountType.SAVINGS;
            default: return AccountType.CHECKING;
        }
    }

}
