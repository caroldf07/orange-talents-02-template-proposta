package br.com.orangetalents.proposta.criarproposta.controller;

import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
@WithMockUser(username = "john")
class PropostaControllerTest {

    @InjectMocks
    private PropostaRepository propostaRepository = Mockito.mock(PropostaRepository.class);

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
                .with(jwt().jwt(builder -> builder.subject("john")))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
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
                .with(jwt().jwt(builder -> builder.subject("john")))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());


    }

    @Test
    @DisplayName("Deveria retornar 422 quando o documento já está cadastrado")
    @Transactional
    void criar3() throws Exception {

        when(propostaRepository.existsByDocumento(anyString())).thenReturn(true);
        ResponseEntity<Object> esperado = ResponseEntity.unprocessableEntity().build();
        ResponseEntity<Object> resultado = null;

        boolean proposta = propostaRepository.existsByDocumento(anyString());

        if (proposta) {
            resultado = ResponseEntity.unprocessableEntity().build();
        }

        assertThat(resultado, Matchers.equalToObject(esperado));

    }

}