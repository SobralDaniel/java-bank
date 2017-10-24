package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> list();

    Customer get(Integer id);

    Customer save(Customer customer);

    void delete(Integer id);

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
