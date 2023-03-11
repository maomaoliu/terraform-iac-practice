package org.example.looam.book.domain.book;

import java.util.List;

import lombok.Builder;

@Builder
public record BookQuery(List<String> ids, String searchKey) {}
