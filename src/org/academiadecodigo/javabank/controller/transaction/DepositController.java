package org.academiadecodigo.javabank.controller.transaction;

public class DepositController extends AbstractAccountTransactionController {

    @Override
    public void submitTransaction(int accountId, double amount) {
        customerService.getAccountService().deposit(accountId, amount);
    }
}
