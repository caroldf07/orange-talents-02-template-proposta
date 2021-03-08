package br.com.orangetalents.proposta.criacaoproposta.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class PropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deveria retornar 201 quando as informações estão ok")
    void criar1() throws Exception {
        URI uri = new URI("/propostas/nova-proposta");
        String json = "{\n" +
                "\t\"documento\":\"82762480981\",\n" +
                "\t\"email\":\"email@email.com\",\n" +
                "\t\"nome\":\"John\",\n" +
                "\t\"endereco\":{\n" +
                "\t\t\"UF\":\"ac\",\n" +
                "\t\t\"cidade\":\"Cidade\",\n" +
                "\t\t\"logradouro\":\"endereço\",\n" +
                "\t\t\"complemento\":\"numero\"\n" +
                "\t},\n" +
                "\t\"salario\":\"1000.00\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/propostas/1"));


    }

    @Test
    @DisplayName("Deveria retornar 400 quando há algum erro com as informações")
    void criar2() throws Exception {
        URI uri = new URI("/propostas/nova-proposta");
        String json = "{\n" +
                "\t\"documento\":\"\",\n" +
                "\t\"email\":\"email@email.com\",\n" +
                "\t\"nome\":\"John\",\n" +
                "\t\"endereco\":{\n" +
                "\t\t\"UF\":\"ac\",\n" +
                "\t\t\"cidade\":\"Cidade\",\n" +
                "\t\t\"logradouro\":\"endereço\",\n" +
                "\t\t\"complemento\":\"numero\"\n" +
                "\t},\n" +
                "\t\"salario\":\"1000.00\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());


    }
}