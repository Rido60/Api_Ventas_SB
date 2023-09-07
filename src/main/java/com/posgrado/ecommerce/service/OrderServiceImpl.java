package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.OrderDto;
import com.posgrado.ecommerce.dto.OrderItemDto;
import com.posgrado.ecommerce.entity.Order;
import com.posgrado.ecommerce.entity.OrderItem;
import com.posgrado.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;
  private ProductService productService;


  @Override
  public String save(OrderDto orderDto) {
    Order order = new Order();
    order.setComment(orderDto.getComment());

    List<OrderItem> items = orderDto.getItems().stream().map(item -> {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setQuantity(item.getQuantity());
      orderItem.setProduct(productService.getById(item.getProductId()));
      return orderItem;
    }).toList();

    order.setItems(items);

    Order orderSaved = orderRepository.save(order);
    return "Order saved successfully with ID: " + orderSaved.getId();
  }

  @Override
  public OrderDto getById(UUID id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(" Order not fount"));
    OrderDto orderDto = new OrderDto();
    orderDto.setComment(order.getComment());
    List<OrderItemDto> itemsDto = order.getItems().stream().map(orderItem -> {
      OrderItemDto itemDto = new OrderItemDto();
      itemDto.setQuantity(orderItem.getQuantity());
      itemDto.setProductId(orderItem.getProduct().getId());
      return itemDto;
    }).toList();
    orderDto.setItems(itemsDto);
    orderDto.setTotalPrice(orderRepository.getTotalPriceByOrderId(id.toString()));
    return orderDto;
  }
}
