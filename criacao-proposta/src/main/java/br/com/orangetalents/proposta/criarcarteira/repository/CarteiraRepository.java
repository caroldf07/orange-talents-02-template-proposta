package br.com.orangetalents.proposta.criarcarteira.repository;

import br.com.orangetalents.proposta.criarcarteira.model.Carteira;
import br.com.orangetalents.proposta.criarcarteira.view.CarteiraEnum;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, String> {
  Cartao findByCartao(String id);

  Optional<Carteira> findByCarteiraAndCartaoId(CarteiraEnum carteira, String cartao);
}
