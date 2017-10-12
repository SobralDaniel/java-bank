package org.academiadecodigo.javabank.model;

public class AbstractModel implements Model {

    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
