package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.utils.Messages;

public class LoginView implements View{

    private Prompt prompt;
    private Bank bank;
    private LoginController controller;

    public LoginView(Prompt prompt, Bank bank) {
        this.prompt = prompt;
        this.bank = bank;
    }

    @Override
    public void show() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        controller.finish(prompt.getUserInput(scanner));
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }
}
