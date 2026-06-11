package com.zen.service.impl;

import com.zen.dto.SalonDTO;
import com.zen.model.Category;
import com.zen.repository.CategoryRepository;
import com.zen.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

     private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory =new Category();
        newCategory.setName(category.getName());
        newCategory.setId(category.getId());
        newCategory.setSalonId(category.getSalonId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return categoryRepository.findBySalonById(id);
    }

    @Override
    public Category getCategoryId(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElse(null);
        if(category==null){
            throw new Exception("category null exist with id"+id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) throws Exception {
        Category category=getCategoryId(id);//both id should be same
        if(category.getSalonId().equals(salonId)){
            throw new Exception("you don't have permission to delete this category");

        }

        categoryRepository.deleteById(id);


    }
}
