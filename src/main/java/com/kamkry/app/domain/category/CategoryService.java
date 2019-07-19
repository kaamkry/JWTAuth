package com.kamkry.app.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public Category get(Integer id) {
        return categoryDao.get(id);
    }

    public List<Category> getByUserId(Integer id) {
        return categoryDao.getByUserId(id);
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void delete(Category category) {
        categoryDao.disable(category);
    }
}
