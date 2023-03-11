package org.example.looam.book.inbound.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import org.example.looam.book.domain.book.Book;
import org.example.looam.book.domain.book.command.CreateBookCommand;
import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookApiTest extends BaseApiTest {
  @Test
  void should_create_book() throws Exception {
    CreateBookCommand command =
        CreateBookCommand.builder()
            .title("西游记")
            .author("吴承恩")
            .price(BigDecimal.ONE)
            .asin("asin-1234")
            .publishedAt(LocalDate.now())
            .build();

    ResultActions resultActions = perform(post("/books"), command).andExpect(status().isOk());

    CreatedResponse createdResponse = parseResponse(resultActions, CreatedResponse.class);

    assertThat(createdResponse.id()).isNotNull();
  }

  @Test
  @Sql("/db/book/two-books.sql")
  void should_find_by_ids() throws Exception {
    String bookId = "c0a81ff2-8581-1dc7-8185-817e88ba0000";
    ResultActions resultActions = perform(get("/books/{id}", bookId)).andExpect(status().isOk());

    Book book = parseResponse(resultActions, Book.class);

    assertThat(book.getId()).isEqualTo(bookId);
  }

  @Test
  @Sql("/db/book/two-books.sql")
  void should_get_all_books_by_ids() throws Exception {
    String bookId1 = "c0a81ff2-8581-1dc7-8185-817e88ba0000";
    String bookId2 = "a04b02de-8d11-11ed-afd7-bbbbb937f043";
    ResultActions resultActions =
        perform(get("/books/all").param("ids", bookId1).param("ids", bookId2))
            .andExpect(status().isOk());

    List<Book> bookPageResult = parseResponse(resultActions, new TypeReference<>() {});

    assertThat(bookPageResult).hasSize(2);
    assertThat(bookPageResult.get(0).getId()).isEqualTo(bookId2);
  }

  @Test
  @Sql("/db/book/two-books.sql")
  void should_get_books_pagable() throws Exception {
    ResultActions resultActions =
        perform(get("/books").param("searchKey", "领域驱动").param("page", "1").param("size", "10"))
            .andExpect(status().isOk());

    PageResult<Book> bookPageResult =
        parseResponse(resultActions, new TypeReference<PageResult<Book>>() {});

    assertThat(bookPageResult.total()).isEqualTo(1);
    assertThat(bookPageResult.data()).hasSize(1);
  }
}
