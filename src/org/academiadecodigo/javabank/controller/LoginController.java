package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.view.View;

public class LoginController implements Controller{

    View view;
    Bank bank;
    Prompt prompt;
    Controller nxtController;


    public LoginController(Bank bank, Prompt prompt) {
        this.bank = bank;
        this.prompt = prompt;
    }

    @Override
    public void init() {
        view.show();
    }

    public void finish(int customerID) {
        bank.setActiveCustomer(bank.getCustomer(customerID));
        nxtController.init();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNxtController(Controller nxtController) {
        this.nxtController = nxtController;
    }
}
