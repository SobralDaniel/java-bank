package org.academiadecodigo.javabank.factories;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.CustomerDTO;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerConverter {

    @Autowired
    private CustomerService customerService;

    public Customer dtoToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        if(customerDTO.getId() != null)
            customer = customerService.findById(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }

    public CustomerDTO customerToDto(Customer customer){
        CustomerDTO customerDTO= new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
