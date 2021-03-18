package br.com.orangetalents.proposta.criarcarteira.controller;

import br.com.orangetalents.proposta.compartilhado.cartao.SelecionaCartao;
import br.com.orangetalents.proposta.criarbiometria.view.CartaoRequest;
import br.com.orangetalents.proposta.criarcarteira.model.Carteira;
import br.com.orangetalents.proposta.criarcarteira.repository.CarteiraRepository;
import br.com.orangetalents.proposta.criarcarteira.view.NovaCarteiraRequest;
import br.com.orangetalents.proposta.criarcarteira.view.NovaCarteiraResponse;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


//Carga de 9 (refatorar)
@RestController
@RequestMapping("/carteiras")
public class CarteiraController implements SelecionaCartao {

    private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);

    //1
    @PersistenceContext
    private EntityManager em;

    //1
    @Autowired
    private NovaCarteiraSistemaExterno novaCarteiraSistemaExterno;

    //1
    @Autowired
    private CarteiraRepository carteiraRepository;

    //1
    private Carteira carteira;

    @Override
    @PostMapping
    public ResponseEntity<RedirectView> selecionaCartao(@Valid CartaoRequest cartaoRequest,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        logger.info("Procurando cartão");
        //1
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());

        //1
        if (cartao != null) {

            assertNotNull(cartao, "Bug ao procurar cartão");
            URI location = uriComponentsBuilder.path("/carteiras/{numeroCartao}")
                    .buildAndExpand(cartao.getId())
                    .toUri();

            logger.info("Redirecionando para cadastro da carteira");
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
    public ResponseEntity<?> criar(@PathVariable("numeroCartao") String numeroCartao,
                                   @RequestBody @Valid NovaCarteiraRequest novaCarteiraRequest,
                                   UriComponentsBuilder uriComponentsBuilder) {

        logger.info("Procurando cartão");
        Cartao cartao = em.find(Cartao.class, numeroCartao);

        //1
        if (cartao == null) {
            assertNull(cartao, "Bug no fluxo de encontrar cartão");
            logger.warn("Cartão não encontrado");
            return ResponseEntity.notFound().build();
        }

        //1
        if (carteiraRepository.findByCarteiraAndCartaoId(novaCarteiraRequest.getCarteira(), cartao.getId()).isPresent()) {
            assertNotNull(carteiraRepository.findByCarteiraAndCartaoId(novaCarteiraRequest.getCarteira(), cartao.getId()),
                    "Bug no fluxo de encontrar carteira");
            logger.warn("Carteira já cadastrada para esse cartão");
            return ResponseEntity.unprocessableEntity().body("Carteira já cadastrada para esse cartão");
        }
        logger.info("Cadastrando nova carteira");

        /*
         * Envia carteira para sistema externo
         * */
        //1
        NovaCarteiraResponse novaCarteiraResponse = novaCarteiraSistemaExterno.novaCarteira(numeroCartao, novaCarteiraRequest);

        carteira = novaCarteiraRequest.toModel(novaCarteiraResponse, cartao);

        carteiraRepository.save(carteira);

        logger.info("Carteira cadastrada");
        return ResponseEntity.created(uriComponentsBuilder
                .path("/carteiras/{numeroCartao}/{idCarteira}")
                .buildAndExpand(numeroCartao, carteira.getId())
                .toUri())
                .build();

    }
}
