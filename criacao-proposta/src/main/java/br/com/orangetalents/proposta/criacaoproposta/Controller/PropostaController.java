package br.com.orangetalents.proposta.criacaoproposta.Controller;

import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criacaoproposta.Model.NovaPropostaRequest;
import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.Repository.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

//carga de 7
@RestController
@RequestMapping("/propostas")
public class PropostaController {

    //1
    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
    //1
    @Autowired
    ResultadoAnalise analise;
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


}
