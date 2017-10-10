package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.*;
import org.academiadecodigo.javabank.view.AccountTransactionView;

import java.util.HashMap;
import java.util.Map;

public class Bootstrap {

    public AuthenticationService generateTestData() {

        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        AuthenticationService authenticationService = new AuthenticationService();
        customerService.setAccountService(accountService);
        authenticationService.setCustomerService(customerService);


        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        customerService.addCustomer(c1);
        customerService.addCustomer(c2);
        customerService.addCustomer(c3);

        return authenticationService;
    }

    public LoginController wireObjects(AuthenticationService authenticationService) {
        CustomerService customerService = authenticationService.getCustomerService();
        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthenticationService(authenticationService);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthenticationService(authenticationService);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceController.setView(balanceView);
        balanceController.setAuthenticationService(authenticationService);
        balanceView.setBalanceController(balanceController);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setCustomerService(customerService);
        newAccountController.setAuthenticationService(authenticationService);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setCustomerService(customerService);
        depositController.setAuthenticationService(authenticationService);
        depositController.setView(depositView);
        withdrawalController.setCustomerService(customerService);
        withdrawalController.setAuthenticationService(authenticationService);
        withdrawalController.setView(withdrawView);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);


        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);

        return loginController;
    }
}
