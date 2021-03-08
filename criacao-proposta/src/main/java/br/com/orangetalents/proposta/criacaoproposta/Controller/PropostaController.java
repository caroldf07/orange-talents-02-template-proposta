package br.com.orangetalents.proposta.criacaoproposta.Controller;

import br.com.orangetalents.proposta.criacaoproposta.Model.NovaPropostaRequest;
import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/nova-proposta")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {

        Proposta proposta = novaPropostaRequest.toModel();
        em.persist(proposta);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).body(proposta);
    }

}
