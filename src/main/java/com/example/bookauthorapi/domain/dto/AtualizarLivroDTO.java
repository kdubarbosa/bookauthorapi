package com.example.bookauthorapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarLivroDTO {

    private String titulo;

    private String descricao;

    private String categoria;

    private AutorDTO autor;

}
