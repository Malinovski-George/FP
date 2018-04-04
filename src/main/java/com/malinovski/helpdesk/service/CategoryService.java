package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(int categoryId);

    List<Category> getCategories();
}
