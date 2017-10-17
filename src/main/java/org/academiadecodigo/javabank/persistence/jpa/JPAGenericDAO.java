package org.academiadecodigo.javabank.persistence.jpa;

import org.academiadecodigo.javabank.manager.JpaSessionManager;
import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.Dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class JPAGenericDAO <T extends AbstractModel> implements Dao<T> {

    public JpaSessionManager sm;
    protected Class<T> tClass;

    @Override
    public List findAll() {
        CriteriaQuery<T> criteriaQuery = sm.getCurrentSession().getCriteriaBuilder().createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return sm.getCurrentSession().find(tClass, id);
    }

    @Override
    public T saveOrUpdate(T modelObject) {
        return sm.getCurrentSession().merge(modelObject);
    }

    @Override
    public void delete(Integer id) {
        sm.getCurrentSession().remove(sm.getCurrentSession().find(tClass, id));
    }

    public void setSm(JpaSessionManager sm) {
        this.sm = sm;
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }
}
