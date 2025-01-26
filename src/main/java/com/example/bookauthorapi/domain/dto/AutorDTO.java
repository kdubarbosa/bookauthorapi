package com.example.bookauthorapi.domain.dto;

import com.example.bookauthorapi.domain.entity.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representação de um autor com seus livros")
public class AutorDTO {

    @Schema(description = "ID do autor", example = "1")
    private Long id;

    @Schema(description = "Nome do autor", example = "Fulano de tal")
    private String nome;

    @Schema(description = "Lista de livros associados ao autor")
    private List<Livro> livros;

}
