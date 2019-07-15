package com.kamkry.app.repository;

import com.kamkry.app.model.AppUser;

import java.util.List;

public interface UserDao {
    AppUser get(String username);
    AppUser get(Integer id);
    List<AppUser> getAll();
    void save(AppUser user);
    void update(AppUser user);
    void delete(Integer id);
}
