package org.academiadecodigo.javabank.dao;

import org.academiadecodigo.javabank.manager.SessionManager;
import org.academiadecodigo.javabank.manager.TransactionManager;
import org.academiadecodigo.javabank.model.AbstractModel;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class GenericDao <T extends AbstractModel>{
    private final Class<T> tClass;
    private SessionManager sm;

    public GenericDao(SessionManager sm,Class<T> tClass) {
        this.sm = sm
        this.tClass = tClass;
    }

    public T findById (Integer id){
        return sm.getCurrentSession().find(tClass,id);
    }

    public List<T> findAll(){
        try {

            CriteriaQuery<T> criteriaQuery = sm.getCurrentSession().getCriteriaBuilder().createQuery(tClass);
            Root<T> root = criteriaQuery.from(tClass);
            return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();

        } finally {
            if (sm.getCurrentSession() != null) {
                sm.getCurrentSession().close();
            }
        }
    }

    public T saveOrUpdate(T modelObject){

    }

    public void delete(){

    }
}
