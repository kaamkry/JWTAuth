package com.kamkry.app.domain.operation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public class OperationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Operation get(Integer id) {
        return (Operation) sessionFactory.getCurrentSession()
                .createQuery("from Operation where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public void save(Operation operation) {
        operation.setCreateDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().save(operation);
    }

    public void update(Operation operation) {
        operation.setModifyDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(operation);
    }

    public void delete(Operation operation) {
        operation.setDeleteDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(operation);
        //sessionFactory.getCurrentSession().delete(operation);
    }
}
