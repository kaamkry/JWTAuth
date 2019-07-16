package com.kamkry.app.service;

import com.kamkry.app.model.AppUser;
import com.kamkry.app.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;


    public AppUser get(String username) {
        return userDao.get(username);
    }

    public AppUser get(Integer id) {
        return userDao.get(id);
    }

    public List<AppUser> getAll() {
        return userDao.getAll();
    }

    public void save(AppUser user) {
        userDao.save(user);
    }

    public void update(AppUser user) {
        userDao.update(user);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return get(username);
    }
}
