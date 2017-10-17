package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.jpa.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaCustomerDAO;

public class AuthServiceImpl implements AuthService {

    private Integer accessingCustomerId;
    private CustomerDao customerDao;

    @Override
    public boolean authenticate(Integer id) {

        try{
            Customer customer = customerDao.findById(id);
            if (customer == null) {
                ((JpaCustomerDAO)customerDao).sm.stopSession();
                return false;
            }

            accessingCustomerId = customer.getId();
            return true;
        } finally {
            ((JpaCustomerDAO)customerDao).sm.stopSession();
        }
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerDao.findById(accessingCustomerId);
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

}
