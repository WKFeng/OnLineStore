package main.com.store.dao;

import main.com.store.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategories() throws Exception;
}
