package br.com.orangetalents.proposta.criacaoproposta.Repository;

import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    boolean existsByDocumento(String documento);
}
