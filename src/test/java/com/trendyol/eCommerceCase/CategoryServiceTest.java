package com.trendyol.eCommerceCase;

import com.trendyol.eCommerceCase.dao.CategoryRepository;
import com.trendyol.eCommerceCase.exceptions.CategoryNameExistException;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import com.trendyol.eCommerceCase.service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCategories()
    {
        List<Category> list = new ArrayList<>();

        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        Category categoryStationary = new Category();
        categoryStationary.setId(1);
        categoryStationary.setName("Stationary");

        list.add(categoryTech);
        list.add(categoryStationary);


        when(categoryRepository.findAll()).thenReturn(list);

        List<Category> categoryList = categoryService.getAll();

        assertEquals(2, categoryList.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void getCategoryById()
    {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        when(categoryRepository.findById(1l)).thenReturn(Optional.of(categoryTech));

        Category category = categoryService.findById(1l);

        assertEquals("Technology", category.getName());
        assertEquals(1l, category.getId());
    }

    @Test
    public void updateCategory() throws CategoryNotFoundException {
        Category currentCategory = new Category();
        currentCategory.setId(1);
        currentCategory.setName("Technology");

        Category newCategory = new Category();
        newCategory.setId(1);
        newCategory.setName("Dress");

        when(categoryRepository.findById(currentCategory.getId())).thenReturn(Optional.of(currentCategory));
        when(categoryRepository.save(newCategory)).thenReturn(newCategory);

        Category category = categoryService.update(newCategory);

        verify(categoryRepository, times(1)).save(newCategory);
        verify(categoryRepository, times(1)).findById(newCategory.getId());

        assertEquals("Dress", category.getName());
    }


    @Test
    public void createCategory() throws  CategoryNameExistException {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        when(categoryRepository.save(categoryTech)).thenReturn(categoryTech);

        Category category = categoryService.create(categoryTech);

        verify(categoryRepository, times(1)).save(categoryTech);

        assertEquals("Technology",category.getName());
    }

    @Test
    public void deleteCategory() throws CategoryNotFoundException {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        when(categoryRepository.findById(1l)).thenReturn(Optional.of(categoryTech));

        categoryService.delete(1l);

        verify(categoryRepository,times(1)).delete(categoryTech);

    }

}
