package com.zen.service;

import com.zen.dto.SalonDTO;
import com.zen.model.Category;
import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryId(Long id) throws Exception;
    void deleteCategoryById(Long id,Long salonId) throws Exception;
}
