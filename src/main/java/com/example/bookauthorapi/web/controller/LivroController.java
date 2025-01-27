package com.example.bookauthorapi.web.controller;

import com.example.bookauthorapi.application.service.LivroService;
import com.example.bookauthorapi.domain.dto.AtualizarLivroDTO;
import com.example.bookauthorapi.domain.dto.InserirLivroDTO;
import com.example.bookauthorapi.domain.dto.LivroDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/livros")
@Tag(name = "Livros", description = "Endpoints para gerenciar livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTO> salvar(@RequestBody InserirLivroDTO inserirLivroDTO) {
        Optional<LivroDTO> livroDTO = livroService.salvar(inserirLivroDTO);
        return livroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarLivroDTO atualizarLivroDTO) {
        Optional<LivroDTO> livroDTO = livroService.atualizar(id, atualizarLivroDTO);
        return livroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodos() {
        List<LivroDTO> livros = livroService.buscarTodos();
        return livros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        Optional<LivroDTO> livroDTO = livroService.buscarPorId(id);
        return livroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
