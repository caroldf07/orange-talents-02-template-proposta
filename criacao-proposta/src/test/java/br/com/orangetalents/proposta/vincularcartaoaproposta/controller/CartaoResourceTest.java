package br.com.orangetalents.proposta.vincularcartaoaproposta.controller;

import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteRequest;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ContextConfiguration(classes = {CartaoResourceTestConfiguration.class})
class CartaoResourceTest {
    @Autowired
    private WireMockServer mockSistemaCartao;
    @Autowired
    private CartaoResource cartaoResource;


    @BeforeEach
    void setUp() throws IOException {
        CartaoResourceTestMock.setUpCartaoResourceMockResponse(mockSistemaCartao);
    }

    @Test
    @DisplayName("Deve retornar uma resposta")
    void liberaCartao() {
        Assertions.assertNotNull(cartaoResource.liberaCartao(
                new AnaliseClienteRequest("08588487080", "John", "1")));
    }
}