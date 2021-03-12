package br.com.orangetalents.proposta.criacaoproposta.controller;

import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criacaoproposta.model.NovaPropostaRequest;
import br.com.orangetalents.proposta.criacaoproposta.model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.repository.PropostaRepository;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

//carga de 7
@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
    //1
    @Autowired
    private ResultadoAnalise analise;
    //1
    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/nova-proposta")
    public ResponseEntity<?> criar(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                   UriComponentsBuilder uriComponentsBuilder) {
        logger.info("Início da criação da proposta");

        //1
        Proposta proposta = novaPropostaRequest.toModel();

        //1
        if (propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento())) {

            logger.warn("Proposta não foi criada");

            Assertions.assertTrue(propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento()),
                    "Proposta que ainda não existe caiu no fluxo errado");

            return ResponseEntity.unprocessableEntity()
                    .body(new ApiExceptionGenerico(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Já existe proposta para esse documento"));

        }
        propostaRepository.save(proposta);

        logger.info("Proposta criada com sucesso, id: " + proposta.getId());

        analise.resultadoAnalise(proposta);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable("id") Long id) {
        logger.info("Consultando proposta");

        Optional<Proposta> proposta = propostaRepository.findById(id);

        //1
        if (proposta.isPresent()) {
            Assertions.assertNotNull(proposta, "Bug ao consultar proposta");
            logger.info("Proposta encontrada");
            return ResponseEntity.ok(proposta.get().fromModelToPropostaConsulta());
        }
        logger.warn("Proposta não encontrada");
        return ResponseEntity.notFound().build();
    }

}
