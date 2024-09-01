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

import br.edu.iftm.rastreamento.dto.RastreamentoDTO;
import br.edu.iftm.rastreamento.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Rastreamento", description = "API de rastreamento de encomendas")
@RequestMapping("/rastreamentos")
public class RastreamentoController {

	@Autowired
	private RastreamentoService rastreamentoService;

	@GetMapping
	@Operation(summary = "Obter todos os rastreamentos", tags = { "Rastreamento" }, responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rastreamentos encontrados", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum rastreamento encontrado") })
	public List<RastreamentoDTO> getAllRastreamentos() {
		return rastreamentoService.getAllRastreamentos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obter rastreamento por ID", tags = { "Rastreamento" }, responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rastreamento encontrado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rastreamento não encontrado") })
	public RastreamentoDTO getRastreamentoById(@PathVariable Long id) {
		RastreamentoDTO rastreamentoDTO = rastreamentoService.getRastreamentoById(id);
		return rastreamentoDTO;
	}

	@PostMapping
	@Operation(summary = "Criar rastreamento", tags = { "Rastreamento" }, responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Rastreamento criado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro na requisição") })
	public RastreamentoDTO createRastreamento(@RequestBody RastreamentoDTO rastreamentoDTO) {
		return rastreamentoService.createRastreamento(rastreamentoDTO);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar rastreamento", tags = { "Rastreamento" }, responses = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rastreamento atualizado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rastreamento não encontrado") })
	public RastreamentoDTO updateRastreamento(@PathVariable Long id, @RequestBody RastreamentoDTO rastreamentoDTO) {
		RastreamentoDTO updatedRastreamento = rastreamentoService.updateRastreamento(id, rastreamentoDTO);
		return updatedRastreamento;
	}

}
