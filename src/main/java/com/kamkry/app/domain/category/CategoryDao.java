package com.kamkry.app.domain.category;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getByUserId(Integer id) {
        return (List<Category>) sessionFactory.getCurrentSession()
                .createQuery("from Category where user.id=:id")
                .setParameter("id", id)
                .getResultList();
    }

    public Category get(Integer id) {
        return (Category) sessionFactory.getCurrentSession().createQuery("from Category where operationId=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public void save(Category category) {
        category.setCreateDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().save(category);
    }

    public void update(Category category) {
        category.setModifyDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(category);
    }

    public void disable(Category category) {
        category.setDeleteDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().update(category);

    }
}

