package br.com.orangetalents.proposta.criacaoproposta.repository;

import br.com.orangetalents.proposta.criacaoproposta.model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.model.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    boolean existsByDocumento(String documento);

    List<Proposta> findByStatusProposta(StatusProposta statusProposta);

    Proposta findByDocumento(String documento);

}
