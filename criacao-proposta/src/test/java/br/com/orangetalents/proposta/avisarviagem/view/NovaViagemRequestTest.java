package br.com.orangetalents.proposta.avisarviagem.view;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.AvisoViagem;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("Test")
class NovaViagemRequestTest {

    @Mock
    Cartao cartao;

    @Test
    @DisplayName("Deve converter de Request para Model")
    void toModel() {

        String data = "20/03/2021";

        NovaViagemRequest novaViagemRequest = new NovaViagemRequest("destino", LocalDate.parse("20/03/2021"));
        AvisoViagem avisoViagemConvertida = novaViagemRequest.toModel(cartao, "ip", "userAgent");
        AvisoViagem avisoViagemEsperada = new AvisoViagem(LocalDate.parse("20/03/2021"), "destino", cartao, "ip",
                "useragent", LocalDateTime.now());

        assertThat(avisoViagemConvertida, samePropertyValuesAs(avisoViagemEsperada));
    }
}