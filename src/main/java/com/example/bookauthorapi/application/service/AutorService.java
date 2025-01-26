package com.example.bookauthorapi.application.service;

import com.example.bookauthorapi.domain.dto.AtualizarAutorDTO;
import com.example.bookauthorapi.domain.dto.AutorDTO;
import com.example.bookauthorapi.domain.dto.InserirAutorDTO;
import com.example.bookauthorapi.domain.entity.Autor;
import com.example.bookauthorapi.domain.mapper.AutorMapper;
import com.example.bookauthorapi.domain.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorMapper mapper;

    public AutorService(AutorRepository autorRepository, AutorMapper mapper) {
        this.autorRepository = autorRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Optional<AutorDTO> salvar(InserirAutorDTO inserirAutorDTO) {
        var autor = autorRepository.save(mapper.toEntity(inserirAutorDTO));
        return Optional.ofNullable(mapper.toDTO(autor));
    }

    @Transactional
    public Optional<AutorDTO> atualizar(Long id, AtualizarAutorDTO atualizarAutorDTO) {
        return autorRepository.findById(id).map(autor -> {
            var autorAtualizado = mapper.toEntity(atualizarAutorDTO, autor);
            autorRepository.save(autorAtualizado);
            return mapper.toDTO(autorAtualizado);
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
                .map(mapper::toDTO)
                .toList();
        return Optional.ofNullable(autorDTOs.isEmpty() ? null : autorDTOs);
    }

    @Transactional(readOnly = true)
    public Optional<AutorDTO> buscarPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(mapper::toDTO);
    }

}