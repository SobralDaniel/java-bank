package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.view.Messages;
import org.academiadecodigo.javabank.view.NewAccountView;
import org.academiadecodigo.javabank.view.View;

import java.util.Map;

public class NewAccountController extends AbstractController {

    private NewAccountView view;

    private Integer newAccountId;
    private AccountFactory accountFactory;
    private AccountService accountService;
    private AccountType accountType;
    private Map<Integer,AccountType> accountTypeMap;

    @Override
    public void init() {

        super.init();
        newAccountId = createAccount();

        view.showAccountID();

    }

    public void setView(NewAccountView view) {
        super.setView(view);
        this.view = view;
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(accountType);

        accountService.add(newAccount, authService.getAccessingCustomer());
        authService.getAccessingCustomer().addAccount(newAccount);

        return newAccount.getId();
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

    public void onAccountTypeSelection(int option){
        if (!accountTypeMap.containsKey(option)){
            throw new IllegalStateException(Messages.SYSTEM_ERROR);
        }
        accountType = accountTypeMap.get(option);
    }

    public void setAccountTypeMap(Map<Integer, AccountType> accountTypeMap) {
        this.accountTypeMap = accountTypeMap;
    }
}
