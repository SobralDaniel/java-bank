package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

public class BankTest {

    public boolean test() {

        AccountService accountService = new AccountService();
        CustomerService customerService = new CustomerService();
        customerService.setAccountService(accountService);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        customerService.addCustomer(c1);
        customerService.addCustomer(c2);

        Account a1 = accountService.openAccount(AccountType.CHECKING);
        Account a2 = accountService.openAccount(AccountType.CHECKING);

        c1.addAccount(a1);
        c2.addAccount(a2);

        accountService.deposit(a1.getId(), 100);
        accountService.deposit(a2.getId(), 100);

        // customerService balance should equal sum of all customers balance
        if (customerService.getBalance() != 200) {
            return false;
        }

        return true;
    }
}
