package br.com.orangetalents.proposta.criarbiometria.model;

import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    private String numeroCartao;

    public CartaoRequest(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public CartaoRequest() {
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
