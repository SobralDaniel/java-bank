package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.controller.DepositController;
import org.academiadecodigo.javabank.controller.WithdrawController;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.utils.Messages;

import java.util.Set;

public class WithdrawView implements View{
    Bank bank;
    Prompt prompt;
    WithdrawController withdrawController;

    public WithdrawView(Bank bank, Prompt prompt) {
        this.bank = bank;
        this.prompt = prompt;
    }

    @Override
    public void show() {
        Set<Integer> accounts = bank.getActiveCustomer().getAccountIds();
        if(accounts.isEmpty()){
            System.out.println(Messages.ERROR_NO_ACCOUNT);
            withdrawController.noAcc();
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
        withdrawController.finish(acc1, amount);
    }

    public void setWithdrawController(WithdrawController withdrawController) {
        this.withdrawController = withdrawController;
    }
}
