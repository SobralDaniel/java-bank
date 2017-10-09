package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.DepositController;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MenuController;
import org.academiadecodigo.javabank.controller.operations.BalanceOperation;
import org.academiadecodigo.javabank.controller.operations.NewAccountOperation;
import org.academiadecodigo.javabank.controller.operations.Operation;
import org.academiadecodigo.javabank.controller.operations.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.operations.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.model.domain.account.AccountType;
import org.academiadecodigo.javabank.view.*;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.model.domain.Customer;
import org.academiadecodigo.javabank.controller.managers.AccountManager;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);
        Prompt prompt = new Prompt(System.in, System.out);

        Customer c1 = new Customer(1,"Rui");
        Customer c2 = new Customer(2,"Sergio");
        Customer c3 = new Customer(3,"Bruno");
        c3.setAccountManager(accountManager);
        c3.openAccount(AccountType.CHECKING);
        c3.openAccount(AccountType.CHECKING);
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        DepositController depositController = new DepositController();
        DepositView depositView = new DepositView(bank, prompt);


        LoginController loginController = new LoginController(bank, prompt);
        LoginView loginView = new LoginView(prompt, bank);
        MenuView menuView = new MenuView(prompt);
        MenuController menuController = new MenuController(bank, prompt);

        loginController.setNxtController(menuController);
        loginController.setView(loginView);
        loginView.setController(loginController);
        menuController.setView(menuView);
        menuView.setMenuController(menuController);
        depositController.setAccountManager(accountManager);
        depositController.setController(menuController);
        depositController.setView(depositView);
        depositView.setDepositController(depositController);



        Map<Integer, Controller> map = new HashMap<>();
        //map.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        map.put(UserOptions.DEPOSIT.getOption(), depositController);
        //map.put(UserOptions.WITHDRAW.getOption(), withdrawController);
        //map.put(UserOptions.OPEN_ACCOUNT.getOption(), openAccController);

        menuController.setOperationsMap(map);

        loginController.init();
    }
}
