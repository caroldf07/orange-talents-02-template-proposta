package br.com.orangetalents.proposta.analisarclientecartao.view;

import br.com.orangetalents.proposta.analisarclientecartao.ResultadoSolicitacao;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

@SpringBootTest
@ActiveProfiles("test")
class AnaliseClienteResponseTest {

    static Stream<Arguments> gerador1() {
        return Stream.of(
                Arguments.of(StatusProposta.ELEGIVEL,
                        new AnaliseClienteResponse("12345667890", "teste", "1", ResultadoSolicitacao.SEM_RESTRICAO)
                ),
                Arguments.of(StatusProposta.NAO_ELEGIVEL,
                        new AnaliseClienteResponse("12345667890", "teste", "1", ResultadoSolicitacao.COM_RESTRICAO)
                )
        );
    }

    static Stream<Arguments> gerador2() {
        return Stream.of(
                Arguments.of(StatusProposta.NAO_ELEGIVEL,
                        new AnaliseClienteResponse("12345667890", "teste", "1", ResultadoSolicitacao.SEM_RESTRICAO)
                ),
                Arguments.of(StatusProposta.ELEGIVEL,
                        new AnaliseClienteResponse("12345667890", "teste", "1", ResultadoSolicitacao.COM_RESTRICAO)
                )
        );
    }

    @DisplayName("Deveria retornar que o Status e o Resultado são iguais")
    @ParameterizedTest
    @MethodSource("gerador1")
    void getStatusProposta1(StatusProposta resultadoSolicitacao, AnaliseClienteResponse analiseClienteResponse) {
        Assertions.assertEquals(resultadoSolicitacao, analiseClienteResponse.getStatusProposta());
    }

    @DisplayName("Deveria retornar que o Status e o Resultado são diferentes")
    @ParameterizedTest
    @MethodSource("gerador2")
    void getStatusProposta2(StatusProposta resultadoSolicitacao, AnaliseClienteResponse analiseClienteResponse) {
        Assertions.assertNotEquals(resultadoSolicitacao, analiseClienteResponse.getStatusProposta());
    }
}