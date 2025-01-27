package com.example.bookauthorapi.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Contract for a generic dto to model mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <M> - Model type parameter.
 */

public interface ModelMapper<D, M> {
    M toModel(D dto);

    D toDto(M model);

    List<M> toModel(List<D> dtoList);

    List<D> toDto(List<M> modelList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget M model, D dto);
}
