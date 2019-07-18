package com.kamkry.app.domain.operation;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class OperationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Operation get(Integer id) {
        Operation operation = (Operation) sessionFactory.getCurrentSession()
                .createQuery("from Operation where operationId=:id")
                .setParameter("id", id)
                .uniqueResult();
        if (operation != null) Hibernate.initialize(operation.getOperationType());
        return operation;
    }

    public List<Operation> getByUserId(Integer id) {
        List<Operation> operations = (List<Operation>) sessionFactory.getCurrentSession()
                .createQuery("from Operation where user.id=:id")
                .setParameter("id", id)
                .getResultList();
        return operations;
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
        //sessionFactory.getCurrentSession().disable(operation);
    }
}
