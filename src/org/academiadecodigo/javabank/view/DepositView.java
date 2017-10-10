package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.controller.DepositController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.utils.Messages;

import java.util.Set;

public class DepositView implements View{
    Bank bank;
    Prompt prompt;
    DepositController depositController;

    public DepositView(Bank bank, Prompt prompt) {
        this.bank = bank;
        this.prompt = prompt;
    }

    @Override
    public void show() {
        Set<Integer> accounts = bank.getActiveCustomer().getAccountIds();
        if(accounts.isEmpty()){
            System.out.println(Messages.ERROR_NO_ACCOUNT);
            depositController.noAcc();
        }
        System.out.println(accounts.toString());
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(accounts);
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);
        int acc1 = prompt.getUserInput(scanner);
        DoubleInputScanner scanner2 = new DoubleInputScanner();
        scanner2.setMessage(Messages.CHOOSE_AMOUNT);
        scanner2.setError(Messages.ERROR_INVALID_AMOUNT);
        double amount = prompt.getUserInput(scanner2);
        depositController.finish(acc1, amount);
    }

    public void setDepositController(DepositController depositController) {
        this.depositController = depositController;
    }
}
