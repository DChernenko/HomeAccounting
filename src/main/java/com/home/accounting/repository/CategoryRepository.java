package com.home.accounting.repository;

import com.home.accounting.entity.Category;
import com.home.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("Select c from Category c where c.name=:name")
    Category findCategoryByName(@Param("name") String name);

    @Query("Select c from Category c where c.user=:user")
    List<Category> findCategoriesByUser(@Param("user") User user);

    @Query("Select c from Category c where c.user=:user and c.name=:name")
    Category findCategoriesByUserAndName(@Param("user") User user,@Param("name") String name);

}
