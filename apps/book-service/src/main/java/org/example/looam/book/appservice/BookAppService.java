package org.example.looam.book.appservice;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.example.looam.book.domain.book.Book;
import org.example.looam.book.domain.book.BookRepository;
import org.example.looam.book.domain.book.BookService;
import org.example.looam.book.domain.book.command.CreateBookCommand;
import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;
import org.example.looam.common.exception.DataNotFoundException;

@Service
@RequiredArgsConstructor
public class BookAppService {
  private final BookService bookService;
  private final BookRepository bookRepository;

  @Transactional
  public CreatedResponse createBook(CreateBookCommand request) {
    return new CreatedResponse(bookService.create(request).getId());
  }

  public Book findById(String id) {
    return bookRepository.findById(id).orElseThrow(DataNotFoundException::new);
  }

  public List<Book> findAll(List<String> ids) {
    return bookRepository.findAllByIds(ids);
  }

  public PageResult<Book> findPage(String searchKey, PageQuery pageQuery) {
    return bookRepository.findPageBySearchKey(searchKey, pageQuery);
  }
}
