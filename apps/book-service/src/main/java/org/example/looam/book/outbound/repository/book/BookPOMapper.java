package org.example.looam.book.outbound.repository.book;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import org.example.looam.book.domain.book.Book;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookPOMapper {

  BookPOMapper MAPPER = Mappers.getMapper(BookPOMapper.class);

  Book toModel(BookPO bookPO);

  BookPO toPO(Book book);
}
