package com.kamkry.app.domain.user;

import com.kamkry.app.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class,UserDao.class})
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;


    @Test
    public void get() {
        assertNotNull(userDao.get(1));
        assertNull(userDao.get(0));
        assertNull(userDao.get(999999999));
    }

    @Test
    public void getByUsername() {
        assertNotNull(userDao.get("kamil"));
        assertNull(userDao.get("sfsguhegshgosihegoiesghsoegihoihseioshegoisehgj"));
    }

    @Test
    public void getAll() {
        assertFalse(userDao.getAll().isEmpty());
    }

    @Test
    public void saveUpdateAndDisable() {
        User user = new User();
        user.setUsername(" -,- ");
        user.setPassword("123");
        userDao.save(user);
        assertNotNull(userDao.get(user.getUsername()));
        user.setUsername(" O,O ");
        userDao.update(user);
        assertNotNull(userDao.get(user.getUsername()));
        userDao.disable(user);
        assertFalse(userDao.get(user.getUsername()).isEnabled());
        userDao.delete(user);
        assertNull(userDao.get(user.getUsername()));
    }

/*    @Test
    public void update() {
    }

    @Test
    public void disable() {
    }*/
}