package br.com.orangetalents.proposta.avisarviagem.view;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.AvisoViagem;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaViagemRequest {
  @NotBlank private String destino;

  @NotNull
  @Future
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataTermino;

  private LocalDateTime instante;

  public NovaViagemRequest(
      @NotBlank String destino,
      @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataTermino) {
    this.destino = destino;
    this.dataTermino =
        LocalDate.parse(dataTermino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    this.instante = LocalDateTime.now();
  }

  /*
   * Criado por conta do Jackson
   * */
  @Deprecated
  public NovaViagemRequest() {}

  public AvisoViagem toModel(Cartao cartao, String ip, String userAgent) {
    return new AvisoViagem(
        this.dataTermino, this.destino, cartao, ip, userAgent, this.instante = LocalDateTime.now());
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  public String getDestino() {
    return destino;
  }

  public LocalDateTime getInstante() {
    return instante;
  }
}
