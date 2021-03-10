package br.com.orangetalents.proposta.analiseclientecartao;

import br.com.orangetalents.proposta.criacaoproposta.Model.StatusProposta;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Assertions;

public class AnaliseClienteResponse {
    private String documento;
    private String nome;
    private String idProposta;
    private ResultadoSolicitacao resultadoSolicitacao;
    private StatusProposta statusProposta;

    /*
     * Deserializando o response body do sistema externo
     * */
    @JsonCreator
    public AnaliseClienteResponse(@JsonProperty("documento") String documento,
                                  @JsonProperty("nome") String nome,
                                  @JsonProperty("idProposta") String idProposta,
                                  @JsonProperty("resultadoSolicitacao") ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public StatusProposta getStatusProposta() {
        if (resultadoSolicitacao == ResultadoSolicitacao.SEM_RESTRICAO) {
            statusProposta = StatusProposta.ELEGIVEL;

            Assertions.assertTrue(resultadoSolicitacao == ResultadoSolicitacao.SEM_RESTRICAO, "Bug na conversão do retorno da análise com a devolutiva");
            return statusProposta;
        }
        statusProposta = StatusProposta.NAO_ELEGIVEL;
        return statusProposta;
    }
}
