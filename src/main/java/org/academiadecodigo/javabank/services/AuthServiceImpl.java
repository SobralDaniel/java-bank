package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private Integer accessingCustomerId;
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean authenticate(Integer id) {

        Customer customer = customerService.findById(id);

        if (customer == null) {
            return false;
        }

        accessingCustomerId = customer.getId();
        return true;
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerService.findById(accessingCustomerId);
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
