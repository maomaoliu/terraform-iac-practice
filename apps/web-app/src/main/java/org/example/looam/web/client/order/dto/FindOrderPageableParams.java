package org.example.looam.web.client.order.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindOrderPageableParams {
  String userId;
  @Positive int page;
  @Positive int size;
}
