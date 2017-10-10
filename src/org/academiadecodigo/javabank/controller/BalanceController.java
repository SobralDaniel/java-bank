package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

public class BalanceController implements Controller {
    View view;
    Controller controller;

    @Override
    public void init() {
        view.show();
    }

    public void finish(){
        controller.init();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
