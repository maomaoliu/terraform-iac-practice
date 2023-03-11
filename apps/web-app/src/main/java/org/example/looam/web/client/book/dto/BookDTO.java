package org.example.looam.web.client.book.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record BookDTO(
    String id,
    String title,
    String description,
    String author,
    String asin,
    BigDecimal price,
    LocalDate publishedAt) {}
