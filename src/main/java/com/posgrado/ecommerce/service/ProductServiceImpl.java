package com.posgrado.ecommerce.service;


import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.mapper.ProductMapper;
import com.posgrado.ecommerce.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;
  private CategoryService categoryService;
  private ProductMapper productMapper;


  @Override
  public Product save(ProductDto dto) {
    //identificar el id de la categoria
    Category category = categoryService.getById(dto.getCategoryId());
    Product product = productMapper.fromDto(dto);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  public Product getById(UUID id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not Found"));

  }

  @Override
  public Product updateById(UUID id, ProductDto dto) {
    Product studentFound = productRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Product not found"));

    if(dto.getName() != studentFound.getName() && dto.getName() != null) {
      studentFound.setName(dto.getName());
    }
    if(dto.getDescription() != null){
      studentFound.setDescription(dto.getDescription());
    }
    if(dto.getImageUrl() != null){
      studentFound.setImageUrl(dto.getImageUrl());
    }
    if(dto.getStock()>0){
      studentFound.setStock(dto.getStock());
    }
    if(dto.getPrice()>0.0){
      studentFound.setPrice(dto.getPrice());
    }
    studentFound.setActive(dto.isActive());
    if(dto.getCategoryId() != null) {
      Category category = categoryService.getById(dto.getCategoryId());
      if (studentFound.getCategory().equals(category.getId())) {
        studentFound.setCategory(studentFound.getCategory());
      } else {
        studentFound.setCategory(category);
      }
    }
    return productRepository.save(studentFound);

  }

  @Override
  public Page<Product> getProduct(Pageable pageable) {

    return productRepository.findAll(pageable);
  }

  @Override
  public PageDto<Product> getProductByCategoryId(UUID id, Pageable pageable) {
    Page<Product> page = productRepository.ListProd(id.toString(),pageable);
    return productMapper.fromEntity(page);
  }

  @Override
  public PageDto<Product> getProductFiltered(Double minPrice, Double maxPrice, Pageable pageable) {
    Page<Product> page = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    return productMapper.fromEntity(page);
  }

}

