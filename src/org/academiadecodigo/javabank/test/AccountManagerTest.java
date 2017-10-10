package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.SavingsAccount;

public class AccountManagerTest {

    public boolean test() {

        AccountService accountService = new AccountService();
        Account ac = accountService.openAccount(AccountType.CHECKING);
        Account as = accountService.openAccount(AccountType.SAVINGS);

        // should be able to deposit
        accountService.deposit(ac.getId(), 10);
        accountService.deposit(as.getId(), SavingsAccount.MIN_BALANCE + 10);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 10) {
            return false;
        }

        // should be able to withdraw
        accountService.withdraw(ac.getId(), 1);
        if (ac.getBalance() != 9) {
            return false;
        }

        // should be able to transfer if sufficient funds are available
        accountService.transfer(as.getId(), ac.getId(), 1);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        // should not be able to transfer if available funds are not sufficient in savings
        accountService.transfer(as.getId(), ac.getId(), 10);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        // should not be able to transfer if available funds are not sufficient in checking
        accountService.transfer(ac.getId(), as.getId(), 11);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        return true;
    }
}
