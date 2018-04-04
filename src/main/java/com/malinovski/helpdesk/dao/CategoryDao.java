package com.malinovski.helpdesk.dao;

import com.malinovski.helpdesk.model.Category;

import java.util.List;

public interface CategoryDao {

    Category getCategoryById(int categoryId);

    List<Category> getCategories();
}
