package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;

public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountFactory accountFactory;
    private AccountService accountService;

    @Override
    public void init() {
        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        authService.getAccessingCustomer().addAccount(newAccount);
        Account account = accountService.addAccount(newAccount);
        return account.getId();
    }

    public Integer getNewAccountId() {
        return newAccountId;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }
}
