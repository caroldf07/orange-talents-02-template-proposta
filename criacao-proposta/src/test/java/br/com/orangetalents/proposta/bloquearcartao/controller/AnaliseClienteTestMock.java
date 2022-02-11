package br.com.orangetalents.proposta.bloquearcartao.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class BloqueioSistemaExternoTestMock {

  static void setUpBloqueioSistemaExternoMockResponse(WireMockServer wireMockServer)
      throws IOException {
    wireMockServer.stubFor(
        WireMock.post(WireMock.urlEqualTo("/api/cartoes/bloqueio"))
            .willReturn(
                WireMock.aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody("{\n" + "  \"resultado\": \"BLOQUEADO\"\n" + "}")));
  }
}
