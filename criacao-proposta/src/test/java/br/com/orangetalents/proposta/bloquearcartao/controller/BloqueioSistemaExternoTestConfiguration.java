package br.com.orangetalents.proposta.bloquearcartao.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class BloqueioSistemaExternoTestConfiguration {

    @Autowired
    private WireMockServer wireMockServer;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer setUpServer() {
        return new WireMockServer(5000);
    }
}
