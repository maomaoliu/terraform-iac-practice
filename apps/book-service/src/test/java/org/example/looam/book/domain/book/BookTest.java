package org.example.looam.book.domain.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import org.example.looam.book.domain.book.command.CreateBookCommand;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

  @Test
  void should_create_book() {
    Book book =
        Book.create(
            CreateBookCommand.builder()
                .title("西游记")
                .author("吴承恩")
                .price(BigDecimal.ONE)
                .asin("asin-1234")
                .publishedAt(LocalDate.now())
                .build());

    assertThat(book.getTitle()).isEqualTo("西游记");
  }
}
