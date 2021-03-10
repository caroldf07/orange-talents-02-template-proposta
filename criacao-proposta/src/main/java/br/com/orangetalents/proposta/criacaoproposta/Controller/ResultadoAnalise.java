package br.com.orangetalents.proposta.criacaoproposta.Controller;

import br.com.orangetalents.proposta.analiseclientecartao.AnaliseCliente;
import br.com.orangetalents.proposta.analiseclientecartao.AnaliseClienteResponse;
import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.Repository.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultadoAnalise {

    //1
    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    //1
    @Autowired
    private PropostaRepository propostaRepository;

    //1
    @Autowired
    private AnaliseCliente analiseCliente;

    public void resultadoAnalise(Proposta proposta) {
        try {
            logger.info("Cliente foi para análise");
            AnaliseClienteResponse analiseClienteResponse = analiseCliente.analiseLiberacaoCartao(proposta.fromModelToRequest());
            logger.info("Retorno da análise");

            proposta.resultadoAnalise(analiseClienteResponse);
            propostaRepository.save(proposta);
            logger.info("Proposta atualizada");

        } catch (FeignException feignException) {
            feignException.contentUTF8();
        }
    }
}
