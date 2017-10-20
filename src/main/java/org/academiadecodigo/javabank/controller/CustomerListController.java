package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerListController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/list"})
    public String customerList(Model model){

        List<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);

        return "customerList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String customerShow(Model model, @PathVariable Integer id){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        List<Account> accounts = customerService.findById(id).getAccounts();
        model.addAttribute("accounts", accounts);

        return "customerShow";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String customerDelete(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customer/";
    }
}
