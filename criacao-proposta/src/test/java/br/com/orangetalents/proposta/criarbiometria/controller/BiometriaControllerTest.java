package br.com.orangetalents.proposta.criarbiometria.controller;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class BiometriaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    Cartao cartao;

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