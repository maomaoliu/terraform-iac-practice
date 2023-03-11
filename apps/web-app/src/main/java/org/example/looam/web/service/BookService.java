package org.example.looam.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageResult;
import org.example.looam.web.client.book.BookServiceClient;
import org.example.looam.web.client.book.dto.BookDTO;
import org.example.looam.web.client.book.dto.CreateBookCommand;
import org.example.looam.web.client.book.dto.FindBookPageableParams;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookServiceClient bookServiceClient;

  public CreatedResponse createBook(CreateBookCommand command) {
    return bookServiceClient.createBook(command);
  }

  public BookDTO findById(String id) {
    return bookServiceClient.findBook(id);
  }

  public PageResult<BookDTO> findPage(int page, int size) {
    return bookServiceClient.findBookPageable(new FindBookPageableParams(page, size));
  }
}
