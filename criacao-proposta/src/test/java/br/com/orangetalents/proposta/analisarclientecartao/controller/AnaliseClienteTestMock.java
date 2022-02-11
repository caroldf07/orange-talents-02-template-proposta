package br.com.orangetalents.proposta.analisarclientecartao.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class AnaliseClienteTestMock {

  static void setUpAnaliseClienteMockResponse(WireMockServer wireMockServer) throws IOException {
    wireMockServer.stubFor(
        WireMock.post(WireMock.urlEqualTo("/api/solicitacao"))
            .willReturn(
                WireMock.aResponse()
                    .withStatus(HttpStatus.CREATED.value())
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(
                        "{\n"
                            + "  \"documento\": \"08588487080\",\n"
                            + "  \"nome\": \"John\",\n"
                            + "  \"resultadoSolicitacao\": \"SEM_RESTRICAO\",\n"
                            + "  \"idProposta\": \"1\"\n"
                            + "}")));
  }
}
