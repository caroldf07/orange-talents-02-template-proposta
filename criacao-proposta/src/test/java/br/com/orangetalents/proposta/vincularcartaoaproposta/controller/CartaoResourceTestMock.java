package br.com.orangetalents.proposta.vincularcartaoaproposta.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class CartaoResourceTestMock {

    static void setUpCartaoResourceMockResponse(WireMockServer wireMockServer) throws IOException {
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/api/cartoes")).willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("{\n" +
                        "  \"id\": \"string\",\n" +
                        "  \"emitidoEm\": \"2021-03-12T13:47:02.883Z\",\n" +
                        "  \"titular\": \"string\",\n" +
                        "  \"bloqueios\": [\n" +
                        "    {\n" +
                        "      \"id\": \"string\",\n" +
                        "      \"bloqueadoEm\": \"2021-03-12T13:47:02.883Z\",\n" +
                        "      \"sistemaResponsavel\": \"string\",\n" +
                        "      \"ativo\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"avisos\": [\n" +
                        "    {\n" +
                        "      \"validoAte\": \"2021-03-12\",\n" +
                        "      \"destino\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"carteiras\": [\n" +
                        "    {\n" +
                        "      \"id\": \"string\",\n" +
                        "      \"email\": \"string\",\n" +
                        "      \"associadaEm\": \"2021-03-12T13:47:02.884Z\",\n" +
                        "      \"emissor\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"parcelas\": [\n" +
                        "    {\n" +
                        "      \"id\": \"string\",\n" +
                        "      \"quantidade\": 0,\n" +
                        "      \"valor\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"limite\": 0,\n" +
                        "  \"renegociacao\": {\n" +
                        "    \"id\": \"string\",\n" +
                        "    \"quantidade\": 0,\n" +
                        "    \"valor\": 0,\n" +
                        "    \"dataDeCriacao\": \"2021-03-12T13:47:02.884Z\"\n" +
                        "  },\n" +
                        "  \"vencimento\": {\n" +
                        "    \"id\": \"string\",\n" +
                        "    \"dia\": 0,\n" +
                        "    \"dataDeCriacao\": \"2021-03-12T13:47:02.884Z\"\n" +
                        "  },\n" +
                        "  \"idProposta\": \"string\"\n" +
                        "}")));
    }
}
