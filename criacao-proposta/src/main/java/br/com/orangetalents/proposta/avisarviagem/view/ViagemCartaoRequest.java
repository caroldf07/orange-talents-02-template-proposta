package br.com.orangetalents.proposta.avisarviagem.view;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class ViagemCartaoRequest {

    private String destino;
    private LocalDate validoAte;

    @JsonCreator
    public ViagemCartaoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
