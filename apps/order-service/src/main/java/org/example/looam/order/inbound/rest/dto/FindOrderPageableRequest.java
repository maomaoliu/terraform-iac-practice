package org.example.looam.order.inbound.rest.dto;

import jakarta.validation.constraints.Positive;

public record FindOrderPageableRequest(String userId, @Positive int page, @Positive int size) {}
