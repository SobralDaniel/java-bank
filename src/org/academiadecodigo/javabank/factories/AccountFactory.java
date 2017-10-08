package org.academiadecodigo.javabank.factories;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;

public class AccountFactory {
    public int accountid = 0;
    public Account createAcc(AccountType accountType){
        switch (accountType){
            case CHECKING:
                accountid++;
                return new CheckingAccount(accountid);
            case SAVINGS:
                accountid++;
                return new SavingsAccount(accountid);
            default:
                accountid++;
                return new CheckingAccount(accountid);
        }
    }
}
