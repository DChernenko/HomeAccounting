package com.home.accounting.repository;

import com.home.accounting.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("Select c from Category c where c.name=:name")
    Category findCategoryByName(@Param("name") String name);
}
