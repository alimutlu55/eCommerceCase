package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.exceptions.CategoryNameExistException;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import com.trendyol.eCommerceCase.model.Product;

import java.util.List;

public interface CategoryService {
    Category create(Category category) throws CategoryNameExistException;
    Category getById(long categoryId);
    Category update(Category category);
    List<Category> getAll();
    Category delete(long categoryId) throws CategoryNotFoundException;
    Category getCategoryByName (String name);
}
