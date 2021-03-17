package br.com.orangetalents.proposta.criarbiometria.controller;

import br.com.orangetalents.proposta.compartilhado.cartao.SelecionaCartao;
import br.com.orangetalents.proposta.criarbiometria.model.Biometria;
import br.com.orangetalents.proposta.criarbiometria.view.CartaoRequest;
import br.com.orangetalents.proposta.criarbiometria.view.NovaBiometriaRequest;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


//Carga de 6
@RestController
@RequestMapping("/biometrias")
public class NovaBiometriaController implements SelecionaCartao {

    private final Logger logger = LoggerFactory.getLogger(NovaBiometriaController.class);

    //1
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    public ResponseEntity<RedirectView> selecionaCartao(@RequestBody @Valid CartaoRequest cartaoRequest,
                                                        UriComponentsBuilder uriComponentsBuilder) {


        //1
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());

        //1
        if (cartao != null) {

            Assertions.assertNotNull(cartao, "Bug ao procurar cartão");
            URI location = uriComponentsBuilder.path("/biometrias/{numeroCartao}")
                    .buildAndExpand(cartao.getId())
                    .toUri();

            logger.info("Redirecionando para cadastro da biometria");
            return ResponseEntity.status(302).body(new RedirectView("redirect: " + location));
        }

        logger.warn("Cartão não encontrado");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<?> cartaoSelecionado() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{numeroCartao}")
    @Transactional
    public ResponseEntity<?> criaBiometria(@PathVariable("numeroCartao") String numeroCartao,
                                           @RequestBody @Valid NovaBiometriaRequest novaBiometriaRequest,
                                           UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = em.find(Cartao.class, numeroCartao);

        //1
        if (cartao != null) {
            Assertions.assertNotNull(cartao, "Bug ao procurar cartão");

            logger.info("Cadastrando biometria");

            //1
            Biometria biometria = novaBiometriaRequest.toModel(cartao);
            em.persist(biometria);

            return ResponseEntity.created(uriComponentsBuilder.path("/biometrias/{numeroCartao}/{idBiometria}")
                    .buildAndExpand(cartao.getId(), biometria.getId())
                    .toUri()).build();
        }

        logger.warn("Cartão não encontrado");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{numeroCartao}/{idBiometria}")
    public ResponseEntity<?> biometriaSelecionada() {
        return ResponseEntity.ok().build();
    }
}
