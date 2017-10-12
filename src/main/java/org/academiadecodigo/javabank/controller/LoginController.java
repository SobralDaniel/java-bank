package org.academiadecodigo.javabank.controller;

public class LoginController extends AbstractController {

    private Controller nextController;

    public void onLogin(int id) {

        if (authService.authenticate(id)) {
            nextController.init();
            return;
        }

        init();

    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

}
