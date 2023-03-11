package org.example.looam.order.inbound.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;
import org.example.looam.order.appservice.OrderAppService;
import org.example.looam.order.domain.order.Order;
import org.example.looam.order.domain.order.command.CreateOrderCommand;
import org.example.looam.order.inbound.rest.dto.FindOrderPageableRequest;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
  private final OrderAppService orderAppService;

  @PostMapping
  public CreatedResponse createOrder(@RequestBody @Valid CreateOrderCommand request) {
    return orderAppService.create(request);
  }

  @GetMapping("/{id}")
  public Order findOrder(@PathVariable String id) {
    return orderAppService.findById(id);
  }

  @GetMapping
  public PageResult<Order> findOrderPageable(@Valid FindOrderPageableRequest request) {
    return orderAppService.findPage(
        request.userId(), new PageQuery(request.page(), request.size()));
  }
}
