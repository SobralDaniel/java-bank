package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

public abstract class AbstractController implements Controller {

    protected View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.show();
    }

}
