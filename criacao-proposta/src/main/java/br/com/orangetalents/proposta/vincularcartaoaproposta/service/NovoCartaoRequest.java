package br.com.orangetalents.proposta.vincularcartaoaproposta.service;

import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Carga 5
@Component
public class NovoCartaoRequest {

    //1
    private final Logger logger = LoggerFactory.getLogger(NovoCartaoRequest.class);

    //1
    @Autowired
    private PropostaRepository propostaRepository;

    //1
    @Autowired
    private PropostaCartao propostaCartao;

    //1
    private List<Proposta> propostasAprovadas = new ArrayList<>();

    @Scheduled(fixedDelayString = "${tempo.scheduled.cartao}")
    private void buscaProposta() {
        propostasAprovadas = propostaRepository.findByStatusProposta(StatusProposta.ELEGIVEL);

        //1
        propostasAprovadas.forEach(proposta -> {
            propostaCartao.vinculaCartaoProposta(proposta);
        });

    }

}
