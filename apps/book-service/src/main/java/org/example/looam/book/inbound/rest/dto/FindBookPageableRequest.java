package org.example.looam.book.inbound.rest.dto;

import jakarta.validation.constraints.Positive;

public record FindBookPageableRequest(String searchKey, @Positive int page, @Positive int size) {}
