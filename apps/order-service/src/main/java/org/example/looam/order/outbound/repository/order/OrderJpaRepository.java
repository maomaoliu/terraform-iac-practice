package org.example.looam.order.outbound.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderPO, String> {
  Page<OrderPO> findAllByUserId(String userId, Pageable pageable);
}
