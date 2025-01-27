package com.example.bookauthorapi.application.service;

import com.example.bookauthorapi.domain.dto.AtualizarLivroDTO;
import com.example.bookauthorapi.domain.dto.InserirLivroDTO;
import com.example.bookauthorapi.domain.dto.LivroDTO;
import com.example.bookauthorapi.domain.entity.Livro;
import com.example.bookauthorapi.domain.repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public Optional<LivroDTO> salvar(InserirLivroDTO inserirLivroDTO) {
        var livro = livroRepository.save(objectMapper.convertValue(inserirLivroDTO, Livro.class));
        return Optional.ofNullable(objectMapper.convertValue(livro, LivroDTO.class));
    }

    @Transactional
    public Optional<LivroDTO> atualizar(Long id, AtualizarLivroDTO atualizarLivroDTO) {
        return livroRepository.findById(id).map(livro -> {
            // Atualiza os campos necessários da entidade existente
            try {
                objectMapper.updateValue(livro, atualizarLivroDTO);
                Livro livroAtualizado = livroRepository.save(livro);
                return objectMapper.convertValue(livroAtualizado, LivroDTO.class);
            } catch (IllegalArgumentException | JsonMappingException e) {
                throw new RuntimeException("Erro ao atualizar o livro: " + e.getMessage(), e);
            }
        });
    }

    @Transactional(readOnly = true)
    public List<LivroDTO> buscarTodos() {
        return livroRepository.findAll().stream()
                .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<LivroDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(livro -> objectMapper.convertValue(livro, LivroDTO.class));
    }

}