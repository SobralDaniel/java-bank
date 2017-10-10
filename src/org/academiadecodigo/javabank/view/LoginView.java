package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.LoginController;

public class LoginView extends AbstractView {

    private LoginController loginController;

    @Override
    public void show() {
        showBankName();
        showLoginPrompt();
    }

    private void showBankName() {
        System.out.println("\n" + Messages.VIEW_LOGIN_WELCOME);
    }

    private void showLoginPrompt() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customerService.getCustomerIds());
        scanner.setMessage("\n" + Messages.VIEW_LOGIN_MESSAGE);
        scanner.setError(Messages.VIEW_LOGIN_ERROR);
        loginController.onLogin(prompt.getUserInput(scanner));

    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}
