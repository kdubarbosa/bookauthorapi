package com.example.bookauthorapi.application.service;

import com.example.bookauthorapi.domain.dto.AtualizarAutorDTO;
import com.example.bookauthorapi.domain.dto.AutorDTO;
import com.example.bookauthorapi.domain.dto.InserirAutorDTO;
import com.example.bookauthorapi.domain.entity.Autor;
import com.example.bookauthorapi.domain.repository.AutorRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public Optional<AutorDTO> salvar(InserirAutorDTO inserirAutorDTO) {
        var autor = autorRepository.save(objectMapper.convertValue(inserirAutorDTO, Autor.class));
        return Optional.ofNullable(objectMapper.convertValue(autor, AutorDTO.class));
    }

    @Transactional
    public Optional<AutorDTO> atualizar(Long id, AtualizarAutorDTO atualizarAutorDTO) {
        return autorRepository.findById(id).map(autor -> {
            try {
                // Atualiza os campos do autor existente
                objectMapper.updateValue(autor, atualizarAutorDTO);
                autorRepository.save(autor);
                return objectMapper.convertValue(autor, AutorDTO.class);
            } catch (IllegalArgumentException | JsonMappingException e) {
                throw new RuntimeException("Erro ao atualizar o autor: " + e.getMessage(), e);
            }
        });
    }


    @Transactional
    public boolean excluir(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Optional<List<AutorDTO>> buscarTodos() {
        List<Autor> autores = autorRepository.findAll();
        List<AutorDTO> autorDTOs = autores.stream()
                .map(autor -> objectMapper.convertValue(autor, AutorDTO.class))
                .toList();
        return Optional.ofNullable(autorDTOs.isEmpty() ? null : autorDTOs);
    }

    @Transactional(readOnly = true)
    public Optional<AutorDTO> buscarPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(a -> objectMapper.convertValue(a, AutorDTO.class));
    }
}
