package com.malinovski.helpdesk.dao.impl;

import com.malinovski.helpdesk.dao.CategoryDao;
import com.malinovski.helpdesk.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    private SessionFactory sessionFactory;

    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Category getCategoryById(int categoryId) {

        Session session = sessionFactory.getCurrentSession();

        return (Category) session.get(Category.class, categoryId);
    }

    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Category.class).list();
    }
}
