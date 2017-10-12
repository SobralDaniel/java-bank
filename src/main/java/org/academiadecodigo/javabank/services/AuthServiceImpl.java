package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

public class AuthServiceImpl implements AuthService {

    private Customer accesingCustomer;
    private CustomerService customerService;

    @Override
    public boolean authenticate(Integer id) {

        accesingCustomer = customerService.findById(id);
        return accesingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accesingCustomer;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
