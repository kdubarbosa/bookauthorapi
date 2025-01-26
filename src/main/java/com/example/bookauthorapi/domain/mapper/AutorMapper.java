package com.example.bookauthorapi.domain.mapper;

import com.example.bookauthorapi.domain.dto.AtualizarAutorDTO;
import com.example.bookauthorapi.domain.dto.AutorDTO;
import com.example.bookauthorapi.domain.dto.InserirAutorDTO;
import com.example.bookauthorapi.domain.entity.Autor;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , uses = {AutorMapper.class})
public interface AutorMapper {

    Autor toEntity(InserirAutorDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Autor toEntity(AtualizarAutorDTO dto, @MappingTarget Autor entity);

    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    AutorDTO toDTO(Autor entity);

}
