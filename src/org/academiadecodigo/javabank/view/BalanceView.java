package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.utils.Messages;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View{
    Bank bank;
    Prompt prompt;
    BalanceController balanceController;
    DecimalFormat df;

    @Override
    public void show() {
        System.out.println("\n" + bank.getActiveCustomer().getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = bank.getActiveCustomer().getAccounts();
        System.out.println(buildBalanceMessage(accounts));

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(bank.getActiveCustomer().getBalance()));
        balanceController.finish();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    private String buildBalanceMessage(Set<Account> accounts){
        StringBuilder str = new StringBuilder();
        for (Account account: accounts) {
            str.append(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }
        return str.toString();
    }
}
