package com.kamkry.app.repository;

import com.kamkry.app.model.AppUser;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public AppUser get(String username) {
        AppUser user = (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where username=:username")
                .setParameter("username", username)
                .uniqueResult();
        if (user != null) Hibernate.initialize(user.getAuthorities());
        return user;
    }

    @Override
    public AppUser get(Integer id) {
        AppUser user = (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where id=:id")
                .setParameter("id", id)
                .uniqueResult();
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

    @Override
    public List<AppUser> getAll() {
        List<AppUser> result = (List<AppUser>) sessionFactory.getCurrentSession()
                .createQuery("from AppUser")
                .getResultList();
        for (AppUser user : result) {
            Hibernate.initialize(user.getAuthorities());
        }
        return result;
    }

    @Override
    public void save(AppUser user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(AppUser updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        AppUser existingUser = get(updatedUser.getId());
        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getUserRoles() != null) existingUser.setUserRoles(updatedUser.getUserRoles());
        existingUser.setEnabled(true);
        session.save(existingUser);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }
}
