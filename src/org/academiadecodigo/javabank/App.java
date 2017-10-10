package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.CustomerService;

public class App {

    private CustomerService customerService;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        customerService = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(customerService);

        // start application
        loginController.init();

    }

}
