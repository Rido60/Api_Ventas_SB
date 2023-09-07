package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.OrderDto;
import com.posgrado.ecommerce.entity.Order;
import java.util.List;
import java.util.UUID;


public interface OrderService {

  String save(OrderDto orderDto);

  OrderDto getById(UUID id);

}
