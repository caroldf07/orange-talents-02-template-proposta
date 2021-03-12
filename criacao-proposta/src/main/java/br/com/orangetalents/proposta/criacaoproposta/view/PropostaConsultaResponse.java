package br.com.orangetalents.proposta.criacaoproposta.view;

import br.com.orangetalents.proposta.criacaoproposta.model.StatusProposta;

public class PropostaConsultaResponse {

    private StatusProposta statusProposta;

    public PropostaConsultaResponse(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
