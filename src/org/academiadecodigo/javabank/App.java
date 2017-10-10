package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;

public class App {

    private AuthenticationService authenticationService;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        authenticationService = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(authenticationService);

        // start application
        loginController.init();

    }

}
