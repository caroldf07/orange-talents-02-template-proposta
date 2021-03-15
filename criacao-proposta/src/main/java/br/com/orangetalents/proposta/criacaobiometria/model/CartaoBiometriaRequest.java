package br.com.orangetalents.proposta.criacaobiometria.model;

import javax.validation.constraints.NotBlank;

public class CartaoBiometriaRequest {

    @NotBlank
    private String numeroCartao;

    public CartaoBiometriaRequest(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public CartaoBiometriaRequest() {
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
