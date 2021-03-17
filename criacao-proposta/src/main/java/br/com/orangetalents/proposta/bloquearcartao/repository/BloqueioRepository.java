package br.com.orangetalents.proposta.bloquearcartao.repository;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, String> {
    Bloqueio findByCartaoId(String id);
}
