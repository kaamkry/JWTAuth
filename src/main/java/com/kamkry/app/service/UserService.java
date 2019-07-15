package com.kamkry.app.service;

import com.kamkry.app.model.AppUser;
import com.kamkry.app.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service(value = "userService")
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;



    public AppUser get(String username){
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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return get(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }
}
