package br.com.orangetalents.proposta.analisarclientecartao.controller;

import br.com.orangetalents.proposta.analisarclientecartao.AnaliseClienteRequest;
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
@ContextConfiguration(classes = {AnaliseClienteTestConfiguration.class})
class AnaliseClienteTest {

    @Autowired
    private WireMockServer mockSistemaAnaliseCliente;
    @Autowired
    private AnaliseCliente analiseCliente;


    @BeforeEach
    void setUp() throws IOException {
        AnaliseClienteTestMock.setUpAnaliseClienteMockResponse(mockSistemaAnaliseCliente);
    }

    @Test
    @DisplayName("Deve retornar uma resposta")
    void analiseLiberacaoCartao() {
        Assertions.assertNotNull(analiseCliente.analiseLiberacaoCartao(new AnaliseClienteRequest("08588487080", "John", "1")));
    }
}