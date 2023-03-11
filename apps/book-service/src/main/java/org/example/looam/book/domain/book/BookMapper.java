package org.example.looam.book.domain.book;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import org.example.looam.book.domain.book.command.CreateBookCommand;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
  BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

  Book toModel(CreateBookCommand command);
}
