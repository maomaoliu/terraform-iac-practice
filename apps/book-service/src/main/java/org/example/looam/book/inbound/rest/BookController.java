package org.example.looam.book.inbound.rest;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.example.looam.book.appservice.BookAppService;
import org.example.looam.book.domain.book.Book;
import org.example.looam.book.domain.book.command.CreateBookCommand;
import org.example.looam.book.inbound.rest.dto.FindBookPageableRequest;
import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookController {
  private final BookAppService bookAppService;

  @PostMapping
  public CreatedResponse createBook(@RequestBody @Valid CreateBookCommand request) {
    return bookAppService.createBook(request);
  }

  @GetMapping("/{id}")
  public Book findBook(@PathVariable String id) {
    return bookAppService.findById(id);
  }

  @GetMapping("/all")
  public List<Book> findBookAll(@RequestParam List<String> ids) {
    return bookAppService.findAll(ids);
  }

  @GetMapping
  public PageResult<Book> findBookPageable(@Valid FindBookPageableRequest request) {
    return bookAppService.findPage(
        request.searchKey(), new PageQuery(request.page(), request.size()));
  }
}
