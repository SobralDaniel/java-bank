package org.academiadecodigo.javabank.controller.transaction;

public class WithdrawalController extends AbstractAccountTransactionController {

    @Override
    public void submitTransaction(int accountId, double amount) {
        customerService.getAccountService().withdraw(accountId, amount);
    }
}
