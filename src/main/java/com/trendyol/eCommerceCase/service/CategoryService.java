package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.exceptions.CategoryNameExistException;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category) throws CategoryNameExistException;
    Category findById(long categoryId);
    Category update(Category category) throws CategoryNotFoundException;
    List<Category> getAll();
    Category delete(long categoryId) throws CategoryNotFoundException;
    Category getCategoryByName (String name);
}
