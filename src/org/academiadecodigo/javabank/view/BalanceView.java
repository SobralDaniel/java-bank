package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View {

    private CustomerService customerService;
    DecimalFormat df = new DecimalFormat("#.##");

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        Customer customer = customerService.getLoginCustomer();
        System.out.println("\n" + customer.getName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }
}
