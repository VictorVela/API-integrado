package br.edu.uniopet.endereco_service.repository;

import br.edu.uniopet.endereco_service.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
