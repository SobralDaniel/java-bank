package org.academiadecodigo.javabank.manager;

public interface TransactionManager {

    void beginRead();

    void beginWrite();

    void commit();

    void rollback();

}
