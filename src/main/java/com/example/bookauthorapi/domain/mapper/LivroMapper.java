package com.example.bookauthorapi.domain.mapper;

import com.example.bookauthorapi.domain.dto.AtualizarLivroDTO;
import com.example.bookauthorapi.domain.dto.InserirLivroDTO;
import com.example.bookauthorapi.domain.dto.LivroDTO;
import com.example.bookauthorapi.domain.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" , uses = {LivroMapper.class})
public interface LivroMapper {

    Livro toEntity(InserirLivroDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Livro toEntity(AtualizarLivroDTO dto, @MappingTarget Livro entity);

    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    LivroDTO toDTO(Livro entity);

}
