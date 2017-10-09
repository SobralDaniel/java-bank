package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.operations.BalanceOperation;
import org.academiadecodigo.javabank.controller.operations.NewAccountOperation;
import org.academiadecodigo.javabank.controller.operations.Operation;
import org.academiadecodigo.javabank.controller.operations.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.operations.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.utils.Messages;

import java.util.HashMap;
import java.util.Map;

public class BankApplication {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int acessingCustomerId;

    public BankApplication(Bank bank) {
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    public void start() {

        mainMenu = buildMainMenu();

        acessingCustomerId = scanCustomerId();
        operationsMap = buildOperationsMap();
        menuLoop();

    }

    private void menuLoop() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();

    }

    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        return prompt.getUserInput(scanner);

    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;

    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;

    }

    public int getAcessingCustomerId() {
        return acessingCustomerId;
    }

    public Bank getBank() {
        return bank;
    }

    public Prompt getPrompt() {
        return prompt;
    }
}
