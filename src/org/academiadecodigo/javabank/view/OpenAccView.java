package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.OpenAccController;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.model.domain.account.AccountType;
import org.academiadecodigo.javabank.utils.Messages;

public class OpenAccView implements View{
    Bank bank;
    OpenAccController openAccController;
    Prompt prompt;

    @Override
    public void show() {
        MenuInputScanner mainMenu = new MenuInputScanner(AccountType.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.CREATED_ACCOUNT + bank.getActiveCustomer().getName());
        openAccController.finish(prompt.getUserInput(mainMenu));

    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setOpenAccController(OpenAccController openAccController) {
        this.openAccController = openAccController;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }
}
