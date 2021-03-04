package com.trendyol.eCommerceCase.controller;

import com.trendyol.eCommerceCase.exceptions.CategoryNameExistException;
import com.trendyol.eCommerceCase.exceptions.CategoryNotFoundException;
import com.trendyol.eCommerceCase.model.Category;
import com.trendyol.eCommerceCase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category category) throws CategoryNameExistException {
        categoryService.create(category);
        return new ResponseEntity<>(category,CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getById(@PathVariable long id){
        Category category = categoryService.getById(id);
        return new ResponseEntity<>(category,OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categoryList = categoryService.getAll();
        return new ResponseEntity<>(categoryList,OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Category> update(@RequestBody Category category){
        Category categoryList = categoryService.update(category);
        return new ResponseEntity<>(categoryList,OK);
    }

    @DeleteMapping("/category")
    public ResponseEntity<Category> delete(@PathVariable long id) throws CategoryNotFoundException {
        Category category = categoryService.delete(id);
        return new ResponseEntity<>(category,OK);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<Category> getById(@PathVariable String name){
        Category category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category,OK);
    }

}
