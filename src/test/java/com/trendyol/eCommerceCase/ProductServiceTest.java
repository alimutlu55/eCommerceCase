package com.trendyol.eCommerceCase;

import com.trendyol.eCommerceCase.dao.CategoryRepository;
import com.trendyol.eCommerceCase.dao.ProductRepository;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.exceptions.ProductNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import com.trendyol.eCommerceCase.model.Product;
import com.trendyol.eCommerceCase.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
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

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProducts()
    {
        List<Product> list = new ArrayList<>();

        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        Category categoryStationary = new Category();
        categoryStationary.setId(1);
        categoryStationary.setName("Stationary");

        Product productOne = new Product(1, "Computer", "Red", categoryTech,"i7,256 SSD");
        Product productTwo = new Product(2, "Mouse", "Blue",categoryTech, "logitech 2018");
        Product productThree = new Product(3, "Pencil", "Black",categoryStationary, "Faber Castell");

        list.add(productOne);
        list.add(productTwo);
        list.add(productThree);

        when(productRepository.findAll()).thenReturn(list);

        List<Product> productList = productServiceImpl.getAll();

        assertEquals(3, productList.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getProductById()
    {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        when(productRepository.findById(1l)).thenReturn(Optional.of(new Product(1, "Computer", "Red", categoryTech,"i7,256 SSD")));

        Product product = productServiceImpl.findById(1);

        assertEquals("Computer", product.getName());
        assertEquals(categoryTech, product.getCategory());
        assertEquals("Red", product.getColor());
    }

    @Test
    public void updateProduct() throws  ProductNotFoundException {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        Product currentProduct = new Product();
        currentProduct.setId(1);
        currentProduct.setName("Computer");
        currentProduct.setCategory(categoryTech);
        currentProduct.setColor("Red");
        currentProduct.setExplanation("i7,256 SSD");

        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("Computer");
        newProduct.setCategory(categoryTech);
        newProduct.setColor("Blue");
        newProduct.setExplanation("i7,256 SSD");

        when(productRepository.findById(currentProduct.getId())).thenReturn(Optional.of(currentProduct));
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        Product updatedProduct = productServiceImpl.update(newProduct);

        verify(productRepository, times(1)).save(newProduct);
        verify(productRepository, times(1)).findById(newProduct.getId());

        assertEquals("Computer", updatedProduct.getName());
        assertEquals(categoryTech, updatedProduct.getCategory());
        assertEquals("Blue", updatedProduct.getColor());

    }


    @Test
    public void createProduct() throws CategoryNotFoundException {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        Product product = new Product();
        product.setName("Computer");
        product.setCategory(categoryTech);
        product.setColor("Red");
        product.setExplanation("i7,256 SSD");

        when(categoryRepository.getCategoryByName(categoryTech.getName())).thenReturn(categoryTech);

        productServiceImpl.create(product);

        verify(productRepository, times(1)).save(product);
        verify(categoryRepository, times(1)).getCategoryByName(categoryTech.getName());
    }

    @Test
    public void deleteProduct() throws ProductNotFoundException {
        Category categoryTech = new Category();
        categoryTech.setId(1);
        categoryTech.setName("Technology");

        Product product = new Product(1, "Computer", "Red", categoryTech,"i7,256 SSD");

        when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        productServiceImpl.delete(1l);

        verify(productRepository,times(1)).delete(product);

    }

}
