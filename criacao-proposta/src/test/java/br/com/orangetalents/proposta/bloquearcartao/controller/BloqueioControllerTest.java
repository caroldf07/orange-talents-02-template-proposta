package br.com.orangetalents.proposta.bloquearcartao.controller;

import br.com.orangetalents.proposta.criarbiometria.view.CartaoRequest;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.VencimentoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase
class BloqueioControllerTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Deve retornar cartão não encontrado")
    void selecionaCartaoNaoEncontraCartao() {
        CartaoRequest cartaoRequest = new CartaoRequest("1234");
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());
        assertNull(cartao);
    }

    @Test
    @DisplayName("Deve retornar cartão encontrado")
    void selecionaCartaoEncontraCartao() {

        Cartao cartaoNovo = new Cartao("12345", LocalDateTime.now(), "none", new HashSet<>(),
                new HashSet<>(), new HashSet<>(), BigDecimal.TEN,
                new VencimentoResponse(null, null, "2021-03-26T21:13:01.257465"));

        em.persist(cartaoNovo);

        CartaoRequest cartaoRequest = new CartaoRequest("12345");
        Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());
        assertNotNull(cartao);
    }

    @Test
    @DisplayName("Deve retornar que o cartão não está bloqueado")
    void bloqueia() {

    }
}