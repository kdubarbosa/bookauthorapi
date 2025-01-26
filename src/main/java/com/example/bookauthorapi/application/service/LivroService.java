package com.example.bookauthorapi.application.service;

import com.example.bookauthorapi.domain.dto.AtualizarLivroDTO;
import com.example.bookauthorapi.domain.dto.InserirLivroDTO;
import com.example.bookauthorapi.domain.dto.LivroDTO;
import com.example.bookauthorapi.domain.mapper.LivroMapper;
import com.example.bookauthorapi.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper mapper;

    @Transactional
    public Optional<LivroDTO> salvar(InserirLivroDTO inserirLivroDTO) {
        var livro = livroRepository.save(mapper.toEntity(inserirLivroDTO));
        return Optional.ofNullable(mapper.toDTO(livro));
    }

    @Transactional
    public Optional<LivroDTO> atualizar(Long id, AtualizarLivroDTO atualizarLivroDTO) {
        return livroRepository.findById(id).map(livro -> {
            var livroAtualizado = mapper.toEntity(atualizarLivroDTO, livro);
            livroRepository.save(livroAtualizado);
            return mapper.toDTO(livroAtualizado);
        });
    }

}
