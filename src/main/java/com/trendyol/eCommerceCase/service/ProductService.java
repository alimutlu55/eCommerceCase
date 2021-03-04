package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.exceptions.ProductNotFoundException;
import com.trendyol.eCommerceCase.model.Product;
import java.util.List;

public interface ProductService {
    Product create (Product product) throws CategoryNotFoundException;
    Product findById(long productId);
    Product delete (long productId) throws ProductNotFoundException;
    Product update (Product product) throws ProductNotFoundException;
    List<Product> getAll();
    List<Product> getByCategoryId (long categoryId);
}
