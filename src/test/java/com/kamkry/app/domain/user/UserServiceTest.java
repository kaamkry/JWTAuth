package com.kamkry.app.domain.user;

import com.kamkry.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class,UserService.class,UserDao.class})
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        final User user = userService.get(1);
        assertNotNull("single user",user);

        final User user2 = userService.get(9999);
        assertNull("not existing user", user2);
    }

    @Test
    public void get1() {
        final User user = userService.get("kamil");
        assertNotNull("single user", user);

        final User user2 = userService.get(" ");
        assertNull("not existing user", user2);
    }

    @Test
    public void getAll() {
        final List<User> userList = userService.getAll();
        assertFalse(userList.isEmpty());
    }

/*    @Test
    public void save() {
        final User user = new User();
        user.setUsername("essasito");
        user.setPassword("muy buenito");
        userService.save(user);
    }

    @Test
    public void update() {
    }

    @Test
    public void disable() {
    }*/
}