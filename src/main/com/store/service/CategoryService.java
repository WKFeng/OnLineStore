package main.com.store.service;

import main.com.store.domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategories() throws Exception;
}
