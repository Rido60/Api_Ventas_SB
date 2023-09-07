package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategorServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAll() {

    return categoryRepository.findAll();
  }

  @Override
  public Category getById(UUID id) {

    return categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not Found"));
  }


}
