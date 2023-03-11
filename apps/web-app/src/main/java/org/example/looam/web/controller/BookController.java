package org.example.looam.web.controller;

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

import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageResult;
import org.example.looam.web.client.book.dto.BookDTO;
import org.example.looam.web.client.book.dto.CreateBookCommand;
import org.example.looam.web.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookController {
  private final BookService bookService;

  @PostMapping
  public CreatedResponse createBook(@RequestBody @Valid CreateBookCommand command) {
    return bookService.createBook(command);
  }

  @GetMapping("/{id}")
  public BookDTO findBook(@PathVariable String id) {
    return bookService.findById(id);
  }

  @GetMapping
  public PageResult<BookDTO> findBookPageable(
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return bookService.findPage(page, size);
  }
}
