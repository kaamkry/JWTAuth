package com.kamkry.app.domain.operation;

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
        return (Operation) sessionFactory.getCurrentSession()
                .createQuery("from Operation where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public List<Operation> getByUserId(Integer id) {
        return (List<Operation>) sessionFactory.getCurrentSession()
                .createQuery("from Operation where user.id=:id")
                .setParameter("id", id)
                .getResultList();
    }

    public void save(Operation operation) {
        operation.setCreateDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().save(operation);
    }

    public void update(Operation operation) {
        operation.setModifyDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(operation);
    }

    public void disable(Operation operation) {
        operation.setDeleteDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(operation);
    }

    public OperationType getOperationType(Integer id) {
        return (OperationType) sessionFactory.getCurrentSession()
                .createQuery("from OperationType where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
