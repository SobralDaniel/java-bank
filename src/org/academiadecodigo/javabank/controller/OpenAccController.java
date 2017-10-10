package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.view.View;

import java.util.Map;

public class OpenAccController implements Controller{
    View view;
    Controller controller;
    Bank bank;
    private Map<Integer, AccountType> map;

    @Override
    public void init() {
        view.show();
    }

    public void finish(int accOption){
        bank.getActiveCustomer().openAccount(map.get(accOption));
        controller.init();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMap(Map<Integer, AccountType> map) {
        this.map = map;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
