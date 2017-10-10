package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;

public class App {

    private Bank bank;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

        Prompt prompt = new Prompt(System.in, System.out);
        AccountManager accountManager = new AccountManager();
        Bootstrap bootstrap = new Bootstrap();
        bank = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(bank, prompt, accountManager);

        // start application
        loginController.init();

    }

}
