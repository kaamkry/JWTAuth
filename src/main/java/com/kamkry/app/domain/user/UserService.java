package com.kamkry.app.domain.user;

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

    public User get(String username) {
        return userDao.get(username);
    }

    public User get(Integer id) {
        return userDao.get(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void disable(User user) {
        userDao.disable(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return get(username);
    }
}
