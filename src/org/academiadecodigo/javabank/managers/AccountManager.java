package org.academiadecodigo.javabank.managers;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;
import org.academiadecodigo.javabank.factories.AccountFactory;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    Map<Integer, Account> accountMap;
    AccountFactory accountFactory = new AccountFactory();

    public AccountManager() {
        this.accountMap = new HashMap<>();
    }

    public Account openAccount(AccountType accountType) {

        Account newAccount = accountFactory.createAcc(accountType);

        accountMap.put(newAccount.getId(), newAccount);
        return newAccount;

    }

    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (account.getAccountType() == AccountType.SAVINGS) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure src account has sufficient funds
        if (srcAccount.getBalance() < amount) {
            return;
        }

        // if src account is savings, we need to keep a minimum balance
        if (srcAccount.getAccountType() == AccountType.SAVINGS &&
                srcAccount.getBalance() < SavingsAccount.MIN_BALANCE + amount) {
            return;
        }

        srcAccount.debit(amount);
        dstAccount.credit(amount);

    }
}
