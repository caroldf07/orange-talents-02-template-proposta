package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Bloqueio {

  @Id private String id;
  private LocalDateTime bloqueadoEm;
  private String sistemaResponsavel;
  private boolean ativo;
  @ManyToOne private Cartao cartao;
  private String ipCliente;
  private String userAgent;

  /*
   * Sistema externo
   * */
  public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
    this.id = id;
    this.bloqueadoEm = bloqueadoEm;
    this.sistemaResponsavel = sistemaResponsavel;
    this.ativo = ativo;
  }

  /*
   * Nosso sistema
   * */
  public Bloqueio(
      @Valid @NotNull Cartao cartao, @NotBlank String ipCliente, @NotBlank String userAgent) {
    this.bloqueadoEm = LocalDateTime.now();
    this.sistemaResponsavel = "api-carol";
    this.cartao = cartao;
    this.ipCliente = ipCliente;
    this.userAgent = userAgent;
  }

  /*
   * Nosso sistema - testes
   * */
  public Bloqueio(
      @NotBlank String id,
      @Valid @NotNull Cartao cartao,
      @NotBlank String ipCliente,
      @NotBlank String userAgent) {
    this.id = id;
    this.bloqueadoEm = LocalDateTime.now();
    this.sistemaResponsavel = "api-carol";
    this.cartao = cartao;
    this.ipCliente = ipCliente;
    this.userAgent = userAgent;
  }

  /*
   * Criado por conta do hibernate
   * */
  @Deprecated
  public Bloqueio() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bloqueio bloqueio = (Bloqueio) o;
    return ativo == bloqueio.ativo
        && Objects.equals(id, bloqueio.id)
        && Objects.equals(bloqueadoEm, bloqueio.bloqueadoEm)
        && Objects.equals(sistemaResponsavel, bloqueio.sistemaResponsavel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sistemaResponsavel);
  }

  public String getId() {
    return id;
  }
}
