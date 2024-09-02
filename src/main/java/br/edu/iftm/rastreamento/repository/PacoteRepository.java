package br.edu.iftm.rastreamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.rastreamento.model.Pacote;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, Long> {
    // Consulta para buscar pacotes pelo status
    List<Pacote> findByStatus(String status);

    // Consulta para buscar pacotes pelo destinatário
    List<Pacote> findByDestinatario(String destinatario);
}
