package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer findById(Integer id);

    List<Customer> findAll();

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
