package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.View;

public abstract class AbstractController implements Controller {

    protected AuthService authService;
    protected View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.show();
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
