package br.com.orangetalents.proposta.criarproposta.controller;

import br.com.orangetalents.proposta.analisarclientecartao.controller.AnaliseCliente;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Carga de 3
@Component
public class ResultadoAnalise {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    //1
    @Autowired
    private PropostaRepository propostaRepository;

    //1
    @Autowired
    private AnaliseCliente analiseCliente;

    public void resultadoAnalise(Proposta proposta) {

        logger.info("Cliente foi para análise");

        //1
        AnaliseClienteResponse analiseClienteResponse = analiseCliente.analiseLiberacaoCartao(proposta.fromModelToRequest());
        logger.info("Retorno da análise");

        proposta.resultadoAnalise(analiseClienteResponse);
        propostaRepository.save(proposta);
        logger.info("Proposta atualizada");

    }
}
