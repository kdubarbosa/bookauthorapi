package com.example.bookauthorapi.web.controller;

import com.example.bookauthorapi.application.service.AutorService;
import com.example.bookauthorapi.domain.dto.AtualizarAutorDTO;
import com.example.bookauthorapi.domain.dto.AutorDTO;
import com.example.bookauthorapi.domain.dto.InserirAutorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/autores")
@Tag(name = "Autores", description = "Endpoints para gerenciar autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    @Operation(summary = "Salvar um autor", description = "Retorna um Autor no banco de dados.")
    public ResponseEntity<AutorDTO> salvar(@RequestBody InserirAutorDTO autorInsertDTO) {
        Optional<AutorDTO> autorDTO = autorService.salvar(autorInsertDTO);

        // Se o autor for salvo com sucesso, retorna 201 (Created)
        return autorDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar autor", description = "Atualiza um registro de autor no BD.")
    public ResponseEntity<AutorDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarAutorDTO atualizarAutorDTO) {
        Optional<AutorDTO> autorDTO = autorService.atualizar(id, atualizarAutorDTO);

        // Se o autor for encontrado e atualizado, retorna 200 (OK)
        return autorDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir autor", description = "Exclui um registro de autor permanentemente no BD.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = autorService.excluir(id);

        return excluido ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores e seus livros")
    public ResponseEntity<List<AutorDTO>> buscarTodos() {
        return autorService.buscarTodos()
                .filter(autores -> !autores.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID.", description = "Retorna um autor pelo seu ID.")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Long id) {
        Optional<AutorDTO> autorDTO = autorService.buscarPorId(id);

        // Se o autor não for encontrado, retorna 404 (Not Found)
        return autorDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
