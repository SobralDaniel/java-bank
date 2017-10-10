package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.managers.AccountManager;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.view.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Bootstrap {

    public Bank generateTestData() {

        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        return bank;
    }

    public LoginController wireObjects(Bank bank, Prompt prompt, AccountManager accountManager) {
        DecimalFormat df = new DecimalFormat("#.##");

        DepositController depositController = new DepositController();
        DepositView depositView = new DepositView(bank, prompt);
        WithdrawController withdrawController = new WithdrawController();
        WithdrawView withdrawView = new WithdrawView(bank, prompt);
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        OpenAccController openAccController = new OpenAccController();
        OpenAccView openAccView = new OpenAccView();


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
        withdrawController.setAccountManager(accountManager);
        withdrawController.setController(menuController);
        withdrawController.setView(withdrawView);
        withdrawView.setWithdrawController(withdrawController);
        balanceView.setBalanceController(balanceController);
        balanceView.setDf(df);
        balanceView.setBank(bank);
        balanceView.setPrompt(prompt);
        balanceController.setController(menuController);
        balanceController.setView(balanceView);
        openAccController.setController(menuController);
        openAccController.setView(openAccView);
        openAccController.setBank(bank);
        openAccView.setOpenAccController(openAccController);
        openAccView.setBank(bank);
        openAccView.setPrompt(prompt);





        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        map.put(UserOptions.DEPOSIT.getOption(), depositController);
        map.put(UserOptions.WITHDRAW.getOption(), withdrawController);
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), openAccController);

        Map<Integer, AccountType> map1 = new HashMap<>();
        map1.put(AccountType.CHECKING.getOption(), AccountType.CHECKING);
        map1.put(AccountType.SAVINGS.getOption(), AccountType.SAVINGS);


        openAccController.setMap(map1);
        menuController.setOperationsMap(map);
        return loginController;
    }
}