package main.com.store.service.impl;

import main.com.store.dao.CategoryDao;
import main.com.store.dao.impl.CategoryDaoImpl;
import main.com.store.domain.Category;
import main.com.store.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAllCategories() throws Exception {
        return categoryDao.findAllCategories();
    }
}
