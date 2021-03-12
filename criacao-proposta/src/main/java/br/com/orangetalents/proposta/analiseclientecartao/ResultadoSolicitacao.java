package br.com.orangetalents.proposta.analiseclientecartao;

import br.com.orangetalents.proposta.criacaoproposta.model.StatusProposta;

import javax.validation.constraints.NotNull;

public enum ResultadoSolicitacao {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private final StatusProposta statusProposta;


    ResultadoSolicitacao(@NotNull StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }
}
