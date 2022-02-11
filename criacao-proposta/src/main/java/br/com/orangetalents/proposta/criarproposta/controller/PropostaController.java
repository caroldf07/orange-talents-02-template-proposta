package br.com.orangetalents.proposta.criarproposta.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.criarproposta.view.NovaPropostaRequest;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// carga de 7
@RestController
@RequestMapping("/propostas")
public class PropostaController {

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
  // 1
  @Autowired private ResultadoAnalise analise;
  // 1
  @Autowired private PropostaRepository propostaRepository;

  @PostMapping("/nova-proposta")
  public ResponseEntity<?> criar(
      @RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
      UriComponentsBuilder uriComponentsBuilder) {
    logger.info("Início da criação da proposta");

    // 1
    Proposta proposta = novaPropostaRequest.toModel();

    // 1
    if (propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento())) {

      logger.warn("Proposta não foi criada");

      assertTrue(
          propostaRepository.existsByDocumento(novaPropostaRequest.getDocumento()),
          "Proposta que ainda não existe caiu no fluxo errado");

      return ResponseEntity.unprocessableEntity()
          .body(
              new ApiExceptionGenerico(
                  HttpStatus.UNPROCESSABLE_ENTITY, "Já existe proposta para esse documento"));
    }
    propostaRepository.save(proposta);

    logger.info("Proposta criada com sucesso, id: " + proposta.getId());

    analise.resultadoAnalise(proposta);

    return ResponseEntity.created(
            uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri())
        .build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> consultar(@PathVariable("id") Long id) {
    logger.info("Consultando proposta");

    Optional<Proposta> proposta = propostaRepository.findById(id);

    // 1
    if (proposta.isPresent()) {
      assertNotNull(proposta, "Bug ao consultar proposta");
      logger.info("Proposta encontrada");
      return ResponseEntity.ok(proposta.get().fromModelToPropostaConsulta());
    }
    logger.warn("Proposta não encontrada");
    return ResponseEntity.notFound().build();
  }
}
