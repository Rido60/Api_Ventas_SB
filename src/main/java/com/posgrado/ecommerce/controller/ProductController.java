package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.exception.response.ErrorResponse;
import com.posgrado.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PRODUCT")
@AllArgsConstructor
@RestController
@RequestMapping("products")

public class ProductController {

    private ProductService productService;
    @Operation(
        summary = "Save a new product"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductDto dto){
      Product product = productService.save(dto);
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(product);
    }
  @Operation(
      summary = "Get Product by Id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Product found",
              content = @Content(
                  mediaType = "aplication/json",
                  schema = @Schema(implementation = Product.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Product not found",
              content = @Content(
                  mediaType = "aplication/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(
      @Parameter(description = "Id of Product to be searched")
      @PathVariable UUID id) {
    Product productFound = productService.getById(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productFound);
  }
  @Hidden
  @GetMapping("/pageable")
  public ResponseEntity<Page<Product>> getProductPageable(@RequestParam int page,@RequestParam int size){
    Pageable pageable = PageRequest.of(page,size);
    Page<Product> productPage= productService.getProduct(pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productPage);
  }
  @Operation(
      summary = "Get filtered product and pageable"
  )
  @GetMapping
  public ResponseEntity<PageDto<Product>> getProductFiltered(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(required = false) Double minPrice,
      @RequestParam(required = false) Double maxPrice,
      @RequestParam(defaultValue = "id") String sortField,
      @RequestParam(defaultValue = "asc") String sortOrder
  ){
    if(minPrice ==null){
      minPrice=Double.MIN_VALUE;
    }
    if(maxPrice ==null){
      maxPrice=Double.MAX_VALUE;
    }

      Sort sort = Sort.by(Sort.Direction.fromString(sortOrder),sortField);

      Pageable pageable = PageRequest.of(page,size,sort );
    PageDto<Product> productPage= productService.getProductFiltered(minPrice, maxPrice,pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productPage);
  }

  @Operation(
      summary = "update data by existing product Id"
  )
  @PutMapping("/{id}")
  public ResponseEntity <Product> updateById(
      @Parameter (description = "Existing Product Id")
      @PathVariable UUID id,
      @RequestBody ProductDto dto){

      Product product = productService.updateById(id,dto);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(product);

  }
  @Operation(
      summary = "List of product by Category"
  )
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<PageDto<Product>> getProduct(
      @Parameter (description = "Id of Category to be searched")
      @PathVariable UUID categoryId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "2") int size
  ){
    //Sort sort = Sort.by(Sort.Direction.fromString(sortOrder),sortField);

    Pageable pageable = PageRequest.of(page,size);
    PageDto<Product> productPage= productService.getProductByCategoryId(categoryId,pageable);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productPage);
  }

}
