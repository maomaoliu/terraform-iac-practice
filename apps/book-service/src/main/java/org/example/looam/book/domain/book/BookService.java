package org.example.looam.book.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.example.looam.book.domain.book.command.CreateBookCommand;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;

  public Book create(CreateBookCommand command) {
    Book book = Book.create(command);
    return bookRepository.save(book);
  }
}
