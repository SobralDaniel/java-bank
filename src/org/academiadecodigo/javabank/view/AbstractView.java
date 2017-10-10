package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.services.CustomerService;

public abstract class AbstractView implements View {

    protected Prompt prompt;
    protected CustomerService customerService;

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
