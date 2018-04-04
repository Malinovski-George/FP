package com.malinovski.helpdesk.service.impl;

import com.malinovski.helpdesk.dao.CategoryDao;
import com.malinovski.helpdesk.model.Category;
import com.malinovski.helpdesk.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoruDao) {
        this.categoryDao = categoruDao;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }
}
