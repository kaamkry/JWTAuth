package com.kamkry.app.domain.user;

import com.kamkry.app.web.controller.user.UserAlreadyExistsException;
import com.kamkry.app.web.controller.user.UserNotFoundException;
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
    SessionFactory sessionFactory;

    public AppUser get(String username) {
        AppUser user = (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where username=:username")
                .setParameter("username", username)
                .uniqueResult();
        if (user == null) throw new UserNotFoundException(username);
        initializeAuthorities(user.getAuthorities());
        return user;
    }

    public AppUser get(Integer id) {
        AppUser user = (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where id=:id")
                .setParameter("id", id)
                .uniqueResult();

        if (user == null) throw new UserNotFoundException();
        initializeAuthorities(user.getAuthorities());
        return user;
    }

    private AppUser getUserFromQuery(String name, Object value) {
        return (AppUser) sessionFactory.getCurrentSession()
                .createQuery("from AppUser where :name=:value")
                .setParameter(name, name)
                .setParameter(value.toString(), value)
                .uniqueResult();
    }

    public List<AppUser> getAll() {
        List<AppUser> result = (List<AppUser>) sessionFactory.getCurrentSession()
                .createQuery("from AppUser")
                .getResultList();
        for (AppUser user : result) {
            initializeAuthorities(user.getAuthorities());
        }
        return result;
    }

    private void initializeAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Hibernate.initialize(authorities);
    }

    public void save(AppUser user) {
        if (get(user.getUsername()) != null)
            throw new UserAlreadyExistsException(user.getUsername());
        user.setEnabled(true);
        sessionFactory.getCurrentSession().save(user);
    }

    public void update(AppUser updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        AppUser existingUser = get(updatedUser.getId());
        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getUserRoles() != null) existingUser.setUserRoles(updatedUser.getUserRoles());
        existingUser.setEnabled(existingUser.isEnabled());
        session.save(existingUser);
    }

    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }
}
