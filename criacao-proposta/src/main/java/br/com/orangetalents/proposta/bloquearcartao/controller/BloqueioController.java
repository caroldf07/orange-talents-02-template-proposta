package br.com.orangetalents.proposta.bloquearcartao.controller;


import br.com.orangetalents.proposta.bloquearcartao.repository.BloqueioRepository;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.compartilhado.cartao.SelecionaCartao;
import br.com.orangetalents.proposta.criarbiometria.controller.NovaBiometriaController;
import br.com.orangetalents.proposta.criarbiometria.model.CartaoRequest;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Bloqueio;
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


//Carga de 7
@RestController
@RequestMapping("/bloqueios")
public class BloqueioController implements SelecionaCartao {

    private final Logger logger = LoggerFactory.getLogger(NovaBiometriaController.class);

    //1
    @PersistenceContext
    private EntityManager em;

    //1
    @Autowired
    private BloqueioSistemaExterno bloqueioSistemaExterno;

    //1
    private Bloqueio bloqueio;

    //1
    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Override
    @PostMapping
    public ResponseEntity<RedirectView> selecionaCartao(@RequestBody @Valid CartaoRequest cartaoRequest,
                                                        UriComponentsBuilder uriComponentsBuilder) {


        logger.info("Procurando cartão");
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());

        //1
        if (cartao != null) {
            Assertions.assertNotNull(cartao, "Bug ao procurar cartão");

            URI location = uriComponentsBuilder.path("/bloqueios/{numeroCartao}")
                    .buildAndExpand(cartao.getId())
                    .toUri();

            logger.info("Redirecionando para solicitação de bloqueio");
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
    public ResponseEntity<?> bloqueia(@PathVariable("numeroCartao") String numeroCartao,
                                      HttpServletRequest httpServletRequest) {

        logger.info("Procurando cartão");
        Cartao cartao = em.find(Cartao.class, numeroCartao);

        logger.info("Verificando bloqueios");
        bloqueio = bloqueioRepository.findByCartaoId(cartao.getId());

        String ip = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");

        //1
        if (bloqueio != null) {
            Assertions.assertNotNull(bloqueio, "Bug no bloqueio de cartão");

            logger.warn("Cartão já está bloqueado");

            return ResponseEntity.unprocessableEntity().build();

            //1
        } else if (cartao != null) {
            Assertions.assertNotNull(cartao, "Bug ao procurar cartão");
            logger.info("Bloqueando cartão");

            /*
             * Notificando sistema externo e alterando status do cartão
             * */
            cartao.alteraStatusCartao(bloqueioSistemaExterno.bloqueiaCartao(cartao.getId(),
                    new BloqueioCartaoRequest("api-carol")));
            em.merge(cartao);

            bloqueio = new Bloqueio(cartao, ip, userAgent);
            em.persist(bloqueio);

            cartao.getBloqueios().add(bloqueio);
            em.merge(cartao);

            logger.info("Cartão bloqueado");
            return ResponseEntity.ok().build();
        }

        logger.warn("Cartão não encontrado");
        return ResponseEntity.notFound().build();

    }
}
