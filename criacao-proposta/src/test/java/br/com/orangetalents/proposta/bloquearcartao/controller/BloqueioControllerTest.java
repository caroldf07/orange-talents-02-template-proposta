package br.com.orangetalents.proposta.bloquearcartao.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.orangetalents.proposta.bloquearcartao.repository.BloqueioRepository;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoResponse;
import br.com.orangetalents.proposta.criarbiometria.view.CartaoRequest;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Bloqueio;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.StatusCartao;
import br.com.orangetalents.proposta.vincularcartaoaproposta.repository.CartaoRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.VencimentoResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase
@EnableConfigurationProperties
@ContextConfiguration(classes = {BloqueioSistemaExternoTestConfiguration.class})
class BloqueioControllerTest {

  @PersistenceContext EntityManager em;

  @Autowired BloqueioRepository bloqueioRepository;

  @Autowired CartaoRepository cartaoRepository;

  @Autowired private WireMockServer mockSistemaExternoBloqueio;

  @Autowired private BloqueioSistemaExterno bloqueioSistemaExterno;

  @BeforeEach
  void setUp() throws IOException {
    BloqueioSistemaExternoTestMock.setUpBloqueioSistemaExternoMockResponse(
        mockSistemaExternoBloqueio);
  }

  @Test
  @DisplayName("Deve retornar cartão não encontrado")
  void seleciona1() {
    CartaoRequest cartaoRequest = new CartaoRequest("1234");
    Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());
    assertNull(cartao);
  }

  @Test
  @DisplayName("Deve retornar cartão encontrado")
  void seleciona2() {
    Cartao cartaoNovo =
        new Cartao(
            "12345",
            LocalDateTime.now(),
            "none",
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>(),
            BigDecimal.TEN,
            new VencimentoResponse(null, null, "2021-03-26T21:13:01.257465"));

    em.persist(cartaoNovo);

    CartaoRequest cartaoRequest = new CartaoRequest("12345");
    Cartao cartao = em.find(Cartao.class, cartaoRequest.getNumeroCartao());
    assertNotNull(cartao);
  }

  @Test
  @DisplayName("Deve retornar que o cartão não está bloqueado")
  void bloqueia1() {
    Cartao cartaoNovo =
        new Cartao(
            "12345",
            LocalDateTime.now(),
            "none",
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>(),
            BigDecimal.TEN,
            new VencimentoResponse(null, null, "2021-03-26T21:13:01.257465"));

    em.persist(cartaoNovo);

    Bloqueio bloqueio = bloqueioRepository.findByCartaoId(cartaoNovo.getId());
    assertNull(bloqueio);
  }

  @Test
  @DisplayName("Deve retornar que bloqueou o cartão")
  void bloqueia2() {
    Cartao cartaoNovo =
        new Cartao(
            "12345",
            LocalDateTime.now(),
            "none",
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>(),
            BigDecimal.TEN,
            new VencimentoResponse(null, null, "2021-03-26T21:13:01.257465"));

    em.persist(cartaoNovo);

    // forçando retorno
    cartaoNovo.alteraStatusCartao(new BloqueioCartaoResponse("BLOQUEADO"));
    em.merge(cartaoNovo);

    Bloqueio bloqueio = new Bloqueio("1", cartaoNovo, "ip", "userAgent");
    em.persist(bloqueio);

    Bloqueio bloqueioRealizado = bloqueioRepository.findByCartaoId(cartaoNovo.getId());
    assertNotNull(bloqueioRealizado);

    Cartao cartaoBloqueado =
        cartaoRepository.findByIdAndStatusCartao("12345", StatusCartao.BLOQUEADO);
    assertNotNull(cartaoBloqueado);
  }
}
