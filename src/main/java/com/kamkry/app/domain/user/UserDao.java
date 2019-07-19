package com.kamkry.app.domain.user;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User get(String username) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User where username=:username")
                .setParameter("username", username)
                .uniqueResult();

        if (user != null) initializeAuthorities(user.getAuthorities());
        return user;
    }

    public User get(Integer id) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User where id=:id")
                .setParameter("id", id)
                .uniqueResult();

        if (user != null) initializeAuthorities(user.getAuthorities());
        return user;
    }

    public List<User> getAll() {
        List<User> result = (List<User>) sessionFactory.getCurrentSession()
                .createQuery("from User")
                .getResultList();
        for (User user : result) {
            initializeAuthorities(user.getAuthorities());
        }
        return result;
    }

    private void initializeAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Hibernate.initialize(authorities);
    }

    public void save(User user) {
        user.setEnabled(true);
        sessionFactory.getCurrentSession().save(user);
    }

    public void update(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User existingUser = get(updatedUser.getId());
        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getUserRoles() != null) existingUser.setUserRoles(updatedUser.getUserRoles());
        existingUser.setEnabled(updatedUser.isEnabled());
        session.save(existingUser);
    }

    public void disable(User user) {
        user.setEnabled(false);
        sessionFactory.getCurrentSession().update(user);
        //sessionFactory.getCurrentSession().disable(get(id));
    }
}
