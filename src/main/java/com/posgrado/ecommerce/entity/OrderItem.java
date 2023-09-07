package com.posgrado.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Types;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

@Data
@Entity
@Table(name="order_items")
public class OrderItem {
  @Id
  @GeneratedValue
  @JdbcTypeCode(Types.VARCHAR)
  private UUID id;
  @Column(nullable = false)
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "product_id")
  Product product;

  @ManyToOne
  @JoinColumn(name = "order_id")
  Order order;
}
