package br.com.orangetalents.proposta.criarproposta.repository;

import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

  boolean existsByDocumento(String documento);

  List<Proposta> findByStatusProposta(StatusProposta statusProposta);

  Proposta findByDocumento(String documento);
}
