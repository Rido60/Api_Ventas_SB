package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

  Product save(ProductDto product);

  Product getById(UUID id);

  Product updateById(UUID id, ProductDto productDto);

  Page<Product> getProduct(Pageable pageable);

  PageDto<Product> getProductByCategoryId(UUID id, Pageable pageable );

  PageDto<Product> getProductFiltered(Double minPrice, Double maxPrice, Pageable pageable);
}
