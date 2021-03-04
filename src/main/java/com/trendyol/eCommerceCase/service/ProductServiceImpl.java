package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.dao.CategoryRepository;
import com.trendyol.eCommerceCase.dao.ProductRepository;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.exceptions.ProductNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import com.trendyol.eCommerceCase.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.trendyol.eCommerceCase.constants.Constants.*;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) throws CategoryNotFoundException {
        Category category = categoryRepository.getCategoryByName(product.getCategory().getName());
        if(category == null){
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND);
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product findById(long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public Product delete(long productId) throws ProductNotFoundException {
        Product product = findById(productId);
        if (product == null) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product update(Product product) throws ProductNotFoundException {
        Product fetchedProduct = findById(product.getId());
        if (product == null) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        fetchedProduct.setName(product.getName());
        fetchedProduct.setColor(product.getColor());
        fetchedProduct.setExplanation(product.getExplanation());
        return productRepository.save(fetchedProduct);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByCategoryId(long categoryId) {
        return productRepository.getByCategoryId(categoryId);
    }
}
