package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

public class AuthenticationService {
    private CustomerService customerService;
    private int loginCustomer;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean isAuthentic(int customerID){
        if(!customerService.getCustomerIds().contains(customerID))
            return false;
        loginCustomer = customerID;
        return true;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Customer getLoginCustomer(){
        return customerService.getCustomer(loginCustomer);
    }
}
