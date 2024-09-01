package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Endereços", description = "API de Endereços")
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	@Operation(summary = "Listar todos os endereços", description = "Lista todos os endereços cadastrados no sistema", tags = {
			"endereços" }, responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado") })
	public List<EnderecoDTO> getAllEnderecos() {
		return enderecoService.getAllEnderecos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar endereço por ID", description = "Busca um endereço pelo ID informado", tags = {
			"endereços" }, responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso"),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Endereço não encontrado") })
	public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id) {
		EnderecoDTO enderecoDTO = enderecoService.getEnderecoById(id);
		return ResponseEntity.ok(enderecoDTO);
	}

	@PostMapping
	@Operation(summary = "Cadastrar endereço", description = "Cadastra um novo endereço no sistema", tags = {
			"endereços" }, responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso") })
	public EnderecoDTO createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		return enderecoService.createEndereco(enderecoDTO);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar endereço", description = "Atualiza um endereço existente no sistema", tags = {
			"endereços" }, responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Endereço não encontrado") })
	public EnderecoDTO updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO updatedEndereco = enderecoService.updateEndereco(id, enderecoDTO);
		return updatedEndereco;
	}
}