package org.academiadecodigo.javabank.model;

import javax.persistence.*;

@MappedSuperclass
public class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
