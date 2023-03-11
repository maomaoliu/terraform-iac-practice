package org.example.looam.book.outbound.repository.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookPO, String> {
  List<BookPO> findAllByIdIn(List<String> ids);

  Page<BookPO> findAllByTitleContaining(String searchKey, Pageable pageable);
}
