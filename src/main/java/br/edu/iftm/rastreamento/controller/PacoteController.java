package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.service.PacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pacotes", description = "API de Pacotes")
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

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
		PacoteDTO pacoteDTO = pacoteService.getPacoteById(id);
		return pacoteDTO;
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
		PacoteDTO updatedPacote = pacoteService.updatePacote(id, pacoteDTO);
		return updatedPacote;
	}
}