package br.edu.iftm.rastreamento.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pacotes", description = "API de Pacotes")
@RequestMapping("/pacotes")
public class PacoteController {

    private final PacoteService pacoteService;

    PacoteController(PacoteService pacoteService) {
        this.pacoteService = pacoteService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os pacotes", description = "Lista todos os pacotes cadastrados no sistema", tags = {
            "pacotes" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pacotes retornados com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum pacote encontrado") })
    public List<PacoteDTO> getAllPacotes() {
        return pacoteService.getAllPacotes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pacote por ID", description = "Busca um pacote pelo ID informado", tags = {
            "pacotes" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pacote retornado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Pacote não encontrado") })
    public PacoteDTO getPacoteById(@PathVariable Long id) {
        return pacoteService.getPacoteById(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar pacote", description = "Cadastra um novo pacote no sistema", tags = {
            "pacotes" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Pacote cadastrado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")) })
    public PacoteDTO createPacote(@RequestBody PacoteDTO pacoteDTO) {
        return pacoteService.createPacote(pacoteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pacote", description = "Atualiza um pacote existente no sistema", tags = {
            "pacotes" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pacote atualizado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Pacote não encontrado") })
    public PacoteDTO updatePacote(@PathVariable Long id, @RequestBody PacoteDTO pacoteDTO) {
        return pacoteService.updatePacote(id, pacoteDTO);
    }

    @GetMapping("/status")
    @Operation(summary = "Buscar pacotes por status", description = "Busca pacotes com o status fornecido", tags = {"pacotes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacotes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum pacote encontrado com o status fornecido")
    })
    public ResponseEntity<List<PacoteDTO>> getPacotesByStatus(@RequestParam String status) {
        try {
            List<PacoteDTO> pacotes = pacoteService.getPacotesByStatus(status);
            return ResponseEntity.ok(pacotes);
        } catch (NaoAcheiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()); // Retorna uma lista vazia com status 404
        }
    }

    @GetMapping("/destinatario")
    @Operation(summary = "Buscar pacotes por destinatário", description = "Busca pacotes com o destinatário fornecido", tags = {"pacotes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacotes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum pacote encontrado com o destinatário fornecido")
    })
    public ResponseEntity<List<PacoteDTO>> getPacotesByDestinatario(@RequestParam String destinatario) {
        try {
            List<PacoteDTO> pacotes = pacoteService.getPacotesByDestinatario(destinatario);
            return ResponseEntity.ok(pacotes);
        } catch (NaoAcheiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()); // Retorna uma lista vazia com status 404
        }
    }
}
