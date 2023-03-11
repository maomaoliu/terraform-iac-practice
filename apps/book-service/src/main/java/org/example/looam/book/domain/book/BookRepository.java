package org.example.looam.book.domain.book;

import java.util.List;
import java.util.Optional;

import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;

public interface BookRepository {
  Book save(Book book);

  Optional<Book> findById(String id);

  List<Book> findAllByIds(List<String> ids);

  PageResult<Book> findPageBySearchKey(String searchKey, PageQuery pageQuery);
}
