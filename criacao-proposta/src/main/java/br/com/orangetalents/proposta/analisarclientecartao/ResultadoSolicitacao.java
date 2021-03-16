package br.com.orangetalents.proposta.analisarclientecartao;

import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;

import javax.validation.constraints.NotNull;

public enum ResultadoSolicitacao {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private final StatusProposta statusProposta;


    ResultadoSolicitacao(@NotNull StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }
}
