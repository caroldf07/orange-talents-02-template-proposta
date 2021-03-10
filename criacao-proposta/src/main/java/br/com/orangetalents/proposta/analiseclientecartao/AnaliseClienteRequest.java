package br.com.orangetalents.proposta.analiseclientecartao;

import org.springframework.boot.jackson.JsonComponent;

import javax.validation.constraints.NotBlank;

@JsonComponent
public class AnaliseClienteRequest {
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    public AnaliseClienteRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }


    /*
     * Criado por conta do jackson
     * */
    @Deprecated
    public AnaliseClienteRequest() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

}
