package org.example.looam.order.outbound.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemPO, String> {
  List<OrderItemPO> findAllByOrderIdIn(List<String> orderIds);
}
