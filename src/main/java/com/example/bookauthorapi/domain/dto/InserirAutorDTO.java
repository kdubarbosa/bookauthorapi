package com.example.bookauthorapi.domain.dto;

import com.example.bookauthorapi.domain.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InserirAutorDTO {

    private String nome;

}
