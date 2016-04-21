package com.home.accounting.service.impl;

import com.home.accounting.entity.Category;
import com.home.accounting.repository.CategoryRepository;
import com.home.accounting.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired/*позволит Spring инициализировать наш сервис*/
    private CategoryRepository categoryRepository;/*объевление нашего сервиса (обратите внимание, что это интерфейс, а не реализация), который позволит нам использовать его бизнес-логику*/

    @Override
    public void addCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void editCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<Category> listCategories() /*получаем все данные с БД*/ {
        return categoryRepository.findAll();
    }
}
