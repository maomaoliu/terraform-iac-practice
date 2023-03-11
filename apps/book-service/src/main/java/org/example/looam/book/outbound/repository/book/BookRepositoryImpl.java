package org.example.looam.book.outbound.repository.book;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.example.looam.book.domain.book.Book;
import org.example.looam.book.domain.book.BookRepository;
import org.example.looam.book.outbound.repository.BaseRepository;
import org.example.looam.common.dto.PageQuery;
import org.example.looam.common.dto.PageResult;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl extends BaseRepository implements BookRepository {
  private final BookJpaRepository bookJpaRepository;
  private final BookPOMapper mapper = BookPOMapper.MAPPER;

  @Override
  public Book save(Book book) {
    BookPO bookPO = bookJpaRepository.save(mapper.toPO(book));
    return mapper.toModel(bookPO);
  }

  @Override
  public Optional<Book> findById(String id) {
    return bookJpaRepository.findById(id).stream().findAny().map(mapper::toModel);
  }

  @Override
  public List<Book> findAllByIds(List<String> ids) {
    return bookJpaRepository.findAllByIdIn(ids).stream().map(mapper::toModel).toList();
  }

  @Override
  public PageResult<Book> findPageBySearchKey(String searchKey, PageQuery pageQuery) {
    Pageable pageable = PageRequest.of(pageQuery.page() - 1, pageQuery.size());
    Page<BookPO> pageBookPOs =
        searchKey == null
            ? bookJpaRepository.findAll(pageable)
            : bookJpaRepository.findAllByTitleContaining(searchKey, pageable);
    return new PageResult<>(
        pageBookPOs.map(mapper::toModel).toList(), pageBookPOs.getTotalElements());
  }
}
