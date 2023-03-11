package org.example.looam.order.appservice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;
import org.example.looam.common.exception.DataNotFoundException;
import org.example.looam.order.domain.order.Order;
import org.example.looam.order.domain.order.OrderRepository;
import org.example.looam.order.domain.order.OrderService;
import org.example.looam.order.domain.order.command.CreateOrderCommand;

@Service
@RequiredArgsConstructor
public class OrderAppService {
  private final OrderService orderService;
  private final OrderRepository orderRepository;

  @Transactional
  public CreatedResponse create(CreateOrderCommand request) {
    return new CreatedResponse(orderService.create(request).getId());
  }

  public PageResult<Order> findPage(String userId, PageQuery pageQuery) {
    return orderRepository.findPageByQuery(userId, pageQuery);
  }

  public Order findById(String id) {
    return orderRepository.findById(id).orElseThrow(DataNotFoundException::new);
  }
}
