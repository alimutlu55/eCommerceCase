package com.trendyol.eCommerceCase.controller;

import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.exceptions.ProductNotFoundException;
import com.trendyol.eCommerceCase.model.Product;
import com.trendyol.eCommerceCase.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> create(@RequestBody Product product) throws CategoryNotFoundException {
        Product createdProduct = productService.create(product);
        return new ResponseEntity<>(createdProduct,CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product,OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> productList = productService.getAll();
        return new ResponseEntity<>(productList,OK);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> update(@RequestBody Product product) throws ProductNotFoundException {
        Product updatedProduct = productService.update(product);
        return new ResponseEntity<>(updatedProduct,OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> delete(@PathVariable long id) throws ProductNotFoundException {
        Product product = productService.delete(id);
        return new ResponseEntity<>(product,OK);
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable long categoryId){
        List<Product> productList = productService.getByCategoryId(categoryId);
        return new ResponseEntity<>(productList,OK);
    }

}
