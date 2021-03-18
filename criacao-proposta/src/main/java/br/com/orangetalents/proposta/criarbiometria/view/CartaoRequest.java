package br.com.orangetalents.proposta.criarbiometria.view;

import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    private String numeroCartao;

    public CartaoRequest(@NotBlank String numeroCartao) {
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
