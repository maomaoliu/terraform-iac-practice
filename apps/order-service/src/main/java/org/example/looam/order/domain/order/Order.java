package org.example.looam.order.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.looam.order.domain.order.command.CreateOrderCommand;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private String id;
  private String userId;
  private String orderNumber;
  private List<OrderItem> orderItems;
  private BigDecimal totalPrice;
  private OrderStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Order create(CreateOrderCommand createOrderCommand) {
    Order order = OrderMapper.MAPPER.toModel(createOrderCommand);
    order.setOrderNumber(buildOrderNumber());
    order.setStatus(OrderStatus.CREATED);
    order.setTotalPrice(calculateTotalPrice(order.orderItems));
    return order;
  }

  private static BigDecimal calculateTotalPrice(List<OrderItem> orderItems) {
    return orderItems.stream()
        .map(item -> item.getDealPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
        .reduce(BigDecimal::add)
        .get();
  }

  private static String buildOrderNumber() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
