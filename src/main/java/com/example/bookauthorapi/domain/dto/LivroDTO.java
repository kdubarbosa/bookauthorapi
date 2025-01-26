package com.example.bookauthorapi.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representação de um livro")
public class LivroDTO {

    @Schema(description = "ID do livro", example = "1")
    private Long id;

    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    private String titulo;

    @Schema(description = "Descrição do livro", example = "Uma fantasia épica sobre a luta entre o bem e o mal.")
    private String descricao;

    @Schema(description = "Categoria do livro", example = "Fantasia")
    private String categoria;

    @Schema(description = "Autor do livro", example = "Fulano de tal")
    private AutorDTO autor;

}
