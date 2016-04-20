package com.home.accounting.service;

import com.home.accounting.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    void editCategory(Category category);

    void deleteCategory(long id);
    /*void delete(Category category);*/
    /* void delete(long[] id);*/

    List<Category> listCategories();


}
