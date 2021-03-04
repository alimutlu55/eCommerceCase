package com.trendyol.eCommerceCase.dao;

import com.trendyol.eCommerceCase.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category getCategoryByName (String name);
}
