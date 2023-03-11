package org.example.looam.order.outbound.repository.order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;
import org.example.looam.order.domain.order.Order;
import org.example.looam.order.domain.order.OrderItem;
import org.example.looam.order.domain.order.OrderRepository;
import org.example.looam.order.outbound.repository.BaseRepository;

import static java.util.stream.Collectors.groupingBy;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl extends BaseRepository implements OrderRepository {
  private final OrderJpaRepository orderJpaRepository;
  private final OrderItemJpaRepository orderItemJpaRepository;
  private final OrderPOMapper mapper = OrderPOMapper.MAPPER;

  @Override
  public Order save(Order order) {
    OrderPO orderPO = orderJpaRepository.save(mapper.toPO(order));
    order.getOrderItems().forEach(item -> item.setOrderId(orderPO.getId()));
    saveOrderItems(order.getOrderItems());
    Order savedOrder = mapper.toModel(orderPO);
    savedOrder.setOrderItems(order.getOrderItems());
    return savedOrder;
  }

  private void saveOrderItems(List<OrderItem> orderItems) {
    List<OrderItemPO> orderItemPOS = orderItems.stream().map(mapper::toPO).toList();
    orderItemJpaRepository.saveAll(orderItemPOS);
  }

  @Override
  public Optional<Order> findById(String id) {
    Optional<Order> orderOptional = orderJpaRepository.findById(id).map(mapper::toModel);
    orderOptional.ifPresent(
        order ->
            order.setOrderItems(
                findOrderItems(List.of(order.getId())).getOrDefault(order.getId(), List.of())));
    return orderOptional;
  }

  private Map<String, List<OrderItem>> findOrderItems(List<String> orderIds) {
    List<OrderItemPO> orderItemPOs = orderItemJpaRepository.findAllByOrderIdIn(orderIds);
    Map<String, List<OrderItemPO>> transform =
        orderItemPOs.stream().collect(groupingBy(OrderItemPO::getOrderId));
    return transform.entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream().map(mapper::toModel).toList()));
  }

  private void queryAndAssembleOrderItem(List<Order> orders) {
    List<String> ids = orders.stream().map(Order::getId).toList();
    Map<String, List<OrderItem>> orderItems = findOrderItems(ids);
    orders.forEach(order -> order.setOrderItems(orderItems.getOrDefault(order.getId(), List.of())));
  }

  @Override
  public PageResult<Order> findPageByQuery(String userId, PageQuery pageQuery) {
    Pageable pageable = PageRequest.of(pageQuery.page() - 1, pageQuery.size());
    Page<OrderPO> pageBookPOs = orderJpaRepository.findAllByUserId(userId, pageable);
    List<Order> orders = pageBookPOs.map(mapper::toModel).toList();
    queryAndAssembleOrderItem(orders);
    return new PageResult<>(orders, pageBookPOs.getTotalElements());
  }
}
