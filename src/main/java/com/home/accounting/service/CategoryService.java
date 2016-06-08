package com.home.accounting.service;

import com.home.accounting.entity.Category;
import com.home.accounting.entity.User;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    void editCategory(Category category);

    void deleteCategory(long id);

    /*void deleteOperation(Category category);*/
    /* void deleteOperation(long[] id);*/
    Category findCategory(long id);

    Category findCategoryByName(String name);

    List<Category> listAllCategories();

    List<Category> listCategoriesByUser(User user);

    boolean isCategoryUnique(Category category);

    boolean isCategoryUnique(Category category, User user);
}
