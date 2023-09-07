package com.posgrado.ecommerce.mapper;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public PageDto<Product> fromEntity(Page<Product> page){
    PageDto<Product> pageDto = new PageDto<>();
    pageDto.setContent(page.getContent());
    pageDto.setPageNumber(page.getNumber());
    pageDto.setPageSize(page.getSize());
    pageDto.setLast(page.isLast());
    pageDto.setTotalPages(page.getTotalPages());
    pageDto.setTotalElements(page.getTotalElements());
    return pageDto;
  }

    public Product fromDto(ProductDto dto){
    //hacer el mapeo
    Product product= new Product();
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setActive(dto.isActive());
    product.setImageUrl(dto.getImageUrl());
    return product;
  }
}
