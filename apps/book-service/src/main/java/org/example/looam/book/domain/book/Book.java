package org.example.looam.book.domain.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.looam.book.domain.book.command.CreateBookCommand;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  private String id;
  private String title;
  private String description;
  private String author;
  private String asin;
  private BigDecimal price;
  private LocalDate publishedAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Book create(CreateBookCommand createBookCommand) {
    Book book = BookMapper.MAPPER.toModel(createBookCommand);
    return book;
  }
}
