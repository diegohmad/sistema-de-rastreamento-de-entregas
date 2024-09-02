package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import br.edu.iftm.rastreamento.service.util.Converters;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private Converters converters;

    public List<PacoteDTO> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList.stream()
                .map(converters::convertToDTO)
                .collect(Collectors.toList());
    }

    public PacoteDTO getPacoteById(Long id) {
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new NaoAcheiException("Pacote com ID " + id + " não encontrado."));
        return converters.convertToDTO(pacote);
    }

    public PacoteDTO createPacote(PacoteDTO pacoteDTO) {
        if (pacoteDTO == null) {
            throw new IllegalArgumentException("PacoteDTO não pode ser nulo.");
        }
        
        Long enderecoId = pacoteDTO.getEndereco().getId();
        if (enderecoId == null) {
            throw new IllegalArgumentException("ID do endereço não pode ser nulo.");
        }

        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new NaoAcheiException("Endereco com ID " + enderecoId + " não encontrado."));
        
        Pacote pacote = converters.convertToEntity(pacoteDTO);
        pacote.setEndereco(endereco);
        Pacote savedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(savedPacote);
    }

    public PacoteDTO updatePacote(Long id, PacoteDTO pacoteDTO) {
        if (id == null || pacoteDTO == null) {
            throw new IllegalArgumentException("ID e PacoteDTO não podem ser nulos.");
        }
        
        if (!pacoteRepository.existsById(id)) {
            throw new NaoAcheiException("Pacote com ID " + id + " não encontrado.");
        }
        
        Pacote pacote = converters.convertToEntity(pacoteDTO);
        pacote.setId(id);
        Pacote updatedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(updatedPacote);
    }

    // Métodos adicionais para as novas consultas
    public List<PacoteDTO> getPacotesByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio.");
        }
        List<Pacote> pacotes = pacoteRepository.findByStatus(status);
        if (pacotes.isEmpty()) {
            throw new NaoAcheiException("Nenhum pacote encontrado com o status: " + status);
        }
        return pacotes.stream().map(converters::convertToDTO).collect(Collectors.toList());
    }

    public List<PacoteDTO> getPacotesByDestinatario(String destinatario) {
        if (destinatario == null || destinatario.trim().isEmpty()) {
            throw new IllegalArgumentException("Destinatário não pode ser nulo ou vazio.");
        }
        List<Pacote> pacotes = pacoteRepository.findByDestinatario(destinatario);
        if (pacotes.isEmpty()) {
            throw new NaoAcheiException("Nenhum pacote encontrado para o destinatário: " + destinatario);
        }
        return pacotes.stream().map(converters::convertToDTO).collect(Collectors.toList());
    }
}
