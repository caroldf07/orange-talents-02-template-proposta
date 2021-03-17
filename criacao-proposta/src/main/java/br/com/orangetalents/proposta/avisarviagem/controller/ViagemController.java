package br.com.orangetalents.proposta.avisarviagem.controller;


import br.com.orangetalents.proposta.avisarviagem.view.NovaViagemRequest;
import br.com.orangetalents.proposta.avisarviagem.view.ViagemCartaoRequest;
import br.com.orangetalents.proposta.compartilhado.cartao.SelecionaCartao;
import br.com.orangetalents.proposta.criarbiometria.model.CartaoRequest;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.AvisoViagem;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


//Carga de 6
@RestController
@RequestMapping("/viagens")
public class ViagemController implements SelecionaCartao {

    private final Logger logger = LoggerFactory.getLogger(ViagemController.class);

    //1
    @PersistenceContext
    private EntityManager em;

    //1
    @Autowired
    private AvisoViagemSistemaExterno avisoViagemSistemaExterno;

    @Override
    @PostMapping
    public ResponseEntity<RedirectView> selecionaCartao(@Valid CartaoRequest cartaoRequest, UriComponentsBuilder uriComponentsBuilder) {
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());

        //1
        if (cartao != null) {

            Assertions.assertNotNull(cartao, "Bug ao procurar cartão");
            URI location = uriComponentsBuilder.path("/viagens/{numeroCartao}")
                    .buildAndExpand(cartao.getId())
                    .toUri();

            logger.info("Redirecionando para registro de nova viagem");
            return ResponseEntity.status(302).body(new RedirectView("redirect: " + location));
        }

        logger.warn("Cartão não encontrado");
        return ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping("/{numeroCartao}")
    public ResponseEntity<?> cartaoSelecionado() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{numeroCartao}")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable("numeroCartao") String numeroCartao,
                                  HttpServletRequest httpServletRequest, @RequestBody @Valid NovaViagemRequest novaViagemRequest) {

        Cartao cartao = em.find(Cartao.class, numeroCartao);

        //1
        if (cartao == null) {
            Assertions.assertNull(cartao, "Bug no fluxo de encontrar cartão");
            logger.warn("Cartão não encontrado");
            return ResponseEntity.notFound().build();
        }

        logger.info("Cadastrando nova viagem");

        String ip = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");


        /*
         * Notificando sistema externo
         * */
        avisoViagemSistemaExterno.avisoViagem(numeroCartao, new ViagemCartaoRequest(novaViagemRequest.getDestino(),
                novaViagemRequest.getDataTermino()));

        //1
        AvisoViagem viagem = novaViagemRequest.toModel(cartao, ip, userAgent);

        em.persist(viagem);

        logger.info("Aviso cadastrado");
        return ResponseEntity.ok().build();
    }

}
