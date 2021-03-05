package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.dao.CategoryRepository;
import com.trendyol.eCommerceCase.exceptions.CategoryNameExistException;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.trendyol.eCommerceCase.constants.Constants.*;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) throws CategoryNameExistException {
        Category findedCategory = categoryRepository.getCategoryByName(category.getName());
        if(findedCategory != null) {
            throw new CategoryNameExistException(CATEGORY_NAME_EXIST);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public Category update(Category category) throws CategoryNotFoundException {
        Category findedCategory = categoryRepository.findById(category.getId()).get();
        if(findedCategory == null){
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND);
        }
        findedCategory.setName(category.getName());
        return categoryRepository.save(findedCategory);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category delete(long categoryId) throws CategoryNotFoundException {
        Category category = findById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND);
        }
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }
}
