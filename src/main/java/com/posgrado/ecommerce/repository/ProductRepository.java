package com.posgrado.ecommerce.repository;

import com.posgrado.ecommerce.entity.Product;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

  Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

  @Query(value = "SELECT * "
      + "FROM products "
      + "WHERE products.category_id = ?1"
      ,nativeQuery = true)
  Page<Product> ListProd(String id,Pageable pageable);



}
