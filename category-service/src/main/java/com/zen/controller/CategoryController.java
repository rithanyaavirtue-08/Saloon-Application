package com.zen.controller;


import com.zen.model.Category;
import com.zen.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryServices;//DependecyInjection(Constructor Injection)

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Category>> getCategoriesBySalon(
            @PathVariable Long id
    ){
        Set<Category> categories=categoryServices.getAllCategoriesBySalon(id);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Long id
    )throws Exception{
        Category categories=categoryServices.getCategoryId(id);
        return ResponseEntity.ok(categories);
    }
}
