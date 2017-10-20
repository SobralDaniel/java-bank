package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerListServlet {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String customerList(Model model){

        List<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);

    return "customerList";
    }
}
