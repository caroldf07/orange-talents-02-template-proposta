package br.com.orangetalents.proposta.analiseclientecartao;

import br.com.orangetalents.proposta.criacaoproposta.Model.StatusProposta;

public enum ResultadoSolicitacao {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private final StatusProposta statusProposta;


    ResultadoSolicitacao(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }
}
