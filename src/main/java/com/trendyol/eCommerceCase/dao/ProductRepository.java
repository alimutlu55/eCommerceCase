package com.trendyol.eCommerceCase.dao;

import com.trendyol.eCommerceCase.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> getByCategoryId (long categoryId);
}
