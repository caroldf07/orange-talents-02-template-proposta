package br.com.orangetalents.proposta.criarbiometria.view;

import br.com.orangetalents.proposta.criarbiometria.model.Biometria;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    private String biometria;

    public NovaBiometriaRequest(@NotBlank String biometria) {
        this.biometria = biometria;
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public NovaBiometriaRequest() {
    }

    @Transactional
    public Biometria toModel(Cartao cartao) {
        return new Biometria(this.biometria, cartao);
    }

    public String getBiometria() {
        return biometria;
    }
}
