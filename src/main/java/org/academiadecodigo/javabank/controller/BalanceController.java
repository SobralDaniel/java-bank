package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.CustomerService;

public class BalanceController extends AbstractController {

    CustomerService customerService;

    public Customer getCustomer() {
        return authService.getAccessingCustomer();
    }

    public double getCustomerBalance() {
       return customerService.getBalance(authService.getAccessingCustomer().getId());
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
