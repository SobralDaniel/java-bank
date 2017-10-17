package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.manager.JpaSessionManager;
import org.academiadecodigo.javabank.manager.JpaTransactionManager;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.jpa.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaAccountDAO;
import org.academiadecodigo.javabank.persistence.jpa.JpaCustomerDAO;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.view.*;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {

    private AuthServiceImpl authService;
    private CustomerService customerService;
    private AccountService accountService;

    public Controller wireObjects(EntityManagerFactory emf) {

        JpaSessionManager sessionManager = new JpaSessionManager();
        sessionManager.setEmf(emf);
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setSm(sessionManager);

        ((JpaCustomerService)customerService).setTm(transactionManager);
        JpaCustomerDAO customerDAO = new JpaCustomerDAO();
        customerDAO.setSm(sessionManager);
        customerDAO.settClass(Customer.class);
        authService.setCustomerDao(customerDAO);
        ((JpaCustomerService)customerService).setCustomerDAO(customerDAO);
        ((JpaAccountService)accountService).setTm(transactionManager);
        JpaAccountDAO accountDAO = new JpaAccountDAO();
        accountDAO.setSm(sessionManager);
        accountDAO.settClass(Account.class);
        ((JpaAccountService)accountService).setAccountDAO(accountDAO);

        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        // wire services
        authService.setCustomerDao(customerDAO);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthService(authService);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceView.setBalanceController(balanceController);
        balanceController.setView(balanceView);
        balanceController.setCustomerService(customerService);
        balanceController.setAuthService(authService);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAccountService(accountService);
        newAccountController.setAuthService(authService);
        newAccountController.setAccountFactory(new AccountFactory());
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setAuthService(authService);
        depositController.setAccountService(accountService);
        depositController.setCustomerService(customerService);
        depositController.setView(depositView);
        withdrawalController.setAuthService(authService);
        withdrawalController.setCustomerService(customerService);
        withdrawalController.setAccountService(accountService);
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

    public void setAuthService(AuthServiceImpl authService) {
        this.authService = authService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
