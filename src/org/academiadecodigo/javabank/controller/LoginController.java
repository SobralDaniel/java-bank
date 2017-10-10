package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.CustomerService;

public class LoginController extends AbstractController {

    private Controller nextController;

    private CustomerService customerService;

    public void onLogin(int id) {
        customerService.setLoginCustomer(id);
        nextController.init();
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
