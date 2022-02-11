package br.com.orangetalents.proposta.analisarclientecartao.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AnaliseClienteTestConfiguration {

  @Autowired private WireMockServer wireMockServer;

  @Bean(initMethod = "start", destroyMethod = "stop")
  public WireMockServer setUpServer() {
    return new WireMockServer(5000);
  }
}
