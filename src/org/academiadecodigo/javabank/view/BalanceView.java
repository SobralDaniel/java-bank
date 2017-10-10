package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View {

    private BalanceController balanceController;
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        System.out.println("\n" + balanceController.getActiveCostumer().getName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        Set<Account> accounts = balanceController.getActiveCostumer().getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(balanceController.getActiveCostumer().getBalance()));
    }

    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }
}
