package org.academiadecodigo.javabank.prompt;

public class PromptOption {
    private Execute execute;

    public PromptOption (Execute execute){
        this.execute = execute;
    }

    public void executeOption (){
        execute.execute();
    }
}
