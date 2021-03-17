package br.com.orangetalents.proposta.vincularcartaoaproposta.service;

import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.controller.CartaoResource;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import br.com.orangetalents.proposta.vincularcartaoaproposta.repository.CartaoRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//6
@Service
public class PropostaCartao {

    //1
    private final Logger logger = LoggerFactory.getLogger(NovoCartaoRequest.class);

    //1
    @Autowired
    private PropostaRepository propostaRepository;

    //1
    @Autowired
    private CartaoRepository cartaoRepository;

    //1
    @Autowired
    private CartaoResource cartaoResource;

    @Transactional
    public void vinculaCartaoProposta(Proposta proposta) {
        //1
        if (proposta.getCartao() == null) {
            Assertions.assertTrue(proposta.getCartao() == null, "Bug ao vincular cartão com proposta");

            logger.info("Buscando cartão");
            //1
            CartaoResponse cartaoResponse = cartaoResource.liberaCartao(proposta.fromModelToRequest());

            Cartao cartao = cartaoResponse.toModel();

            cartao.propostaCriada(proposta);
            cartaoRepository.save(cartao);

            proposta.cartaoCriado(cartao);
            propostaRepository.save(proposta);

            logger.info("Proposta atualizada");
        }
    }
}
