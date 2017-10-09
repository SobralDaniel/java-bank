package org.academiadecodigo.javabank.controller.operations;

import org.academiadecodigo.javabank.view.BankApplication;
import org.academiadecodigo.javabank.utils.Messages;
import org.academiadecodigo.javabank.model.domain.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceOperation extends AbstractBankOperation {

    DecimalFormat df = new DecimalFormat("#.##");


    public BalanceOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {

        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account: accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));

    }
}
