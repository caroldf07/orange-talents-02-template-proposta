package br.com.orangetalents.proposta.vincularcartaoaproposta.service;

import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.criarproposta.model.Endereco;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.stream.Stream;

@ActiveProfiles("test")
class PropostaCartaoTest {

    static Stream<Arguments> gerador1() {
        return Stream.of(Arguments.of(new Proposta("email@email", "12345678901",
                        "Teste", new Endereco("SP", "São Paulo",
                        "Rua ali da esquina", "01"), BigDecimal.TEN),
                null));
    }

    static Stream<Arguments> gerador2() {
        return Stream.of(Arguments.of(new Proposta("email@email", "12345678901",
                        "Teste", new Endereco("SP", "São Paulo",
                        "Rua ali da esquina", "01"), BigDecimal.TEN),
                StatusProposta.ELEGIVEL));
    }

    @DisplayName("O cartão deve ser nulo")
    @ParameterizedTest
    @MethodSource("gerador1")
    void vinculaCartaoProposta1(Proposta proposta, String cartao) {

        Assertions.assertEquals(proposta.getCartao(), cartao);
    }

    @DisplayName("O cartão não deve ser nulo")
    @ParameterizedTest
    @MethodSource("gerador1")
    void vinculaCartaoProposta2(Proposta proposta, String cartao) {

        proposta.resultadoAnalise(new AnaliseClienteResponse(StatusProposta.ELEGIVEL));

        Assertions.assertEquals(proposta.getCartao(), cartao);
    }
}