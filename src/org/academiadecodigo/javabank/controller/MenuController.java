package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.operations.Operation;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.view.View;

import java.util.Map;

public class MenuController implements Controller{
    private View view;
    private Bank bank;
    private Prompt prompt;
    private Controller nxtController;
    private Map<Integer, Controller> operationsMap;

    public MenuController(Bank bank, Prompt prompt) {
        this.bank = bank;
        this.prompt = prompt;
    }

    @Override
    public void init() {
        view.show();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNxtController(Controller nxtController) {
        this.nxtController = nxtController;
    }

    public void setOperationsMap(Map<Integer, Controller> operationsMap) {
        this.operationsMap = operationsMap;
    }

    public void finish(int userChoice){
        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }
        operationsMap.get(userChoice).init();
    }


}
