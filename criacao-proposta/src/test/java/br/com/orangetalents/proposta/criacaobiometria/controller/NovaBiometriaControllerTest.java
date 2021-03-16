package br.com.orangetalents.proposta.criacaobiometria.controller;

import br.com.orangetalents.proposta.criacaobiometria.model.Biometria;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class NovaBiometriaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    Cartao cartao = Mockito.mock(Cartao.class);

    @Test
    void criaBiometria() throws Exception {
        String json = "{\n" +
                "\t\"biometria\":\"teste\"\n" +
                "}";

        URI location = new URI("/biometrias/{idBiometria}");

        mockMvc.perform(
                MockMvcRequestBuilders.post(location)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}