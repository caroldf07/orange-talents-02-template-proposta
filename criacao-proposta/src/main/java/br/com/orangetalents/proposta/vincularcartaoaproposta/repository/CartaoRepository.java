package br.com.orangetalents.proposta.vincularcartaoaproposta.repository;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
