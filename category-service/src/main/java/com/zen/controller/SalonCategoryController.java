package com.zen.controller;

import com.zen.dto.SalonDTO;
import com.zen.model.Category;
import com.zen.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryServices;//DependecyInjection(Constructor Injection)

    @PostMapping()
    public ResponseEntity<Category> createCategory(
           @RequestBody Category category
    ){
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        Category savedCategories=categoryServices.saveCategory(category,salonDTO);
        return ResponseEntity.ok(savedCategories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id ) throws Exception {
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
       categoryServices.deleteCategoryById(id,salonDTO.getId());
        return ResponseEntity.ok("Category Deleted successfully");
    }


}
