package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.controller.managers.AccountManager;
import org.academiadecodigo.javabank.view.View;

public class DepositController implements Controller{
    View view;
    Controller controller;
    AccountManager accountManager;

    @Override
    public void init() {
        view.show();
    }

    public void finish(int acc, double amount){
        accountManager.deposit(acc, amount);
        controller.init();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
}
