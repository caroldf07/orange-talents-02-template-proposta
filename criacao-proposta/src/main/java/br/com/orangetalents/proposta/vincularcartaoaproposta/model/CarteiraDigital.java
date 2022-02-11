package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

@Entity
public class CarteiraDigital {
  @Id private String id;
  @Email private String email;
  private LocalDateTime associadaEm;
  private String emissor;
  @ManyToOne private Cartao cartao;

  public CarteiraDigital(
      String id, @Email String email, LocalDateTime associadaEm, String emissor) {
    this.id = id;
    this.email = email;
    this.associadaEm = associadaEm;
    this.emissor = emissor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CarteiraDigital that = (CarteiraDigital) o;
    return Objects.equals(id, that.id) && Objects.equals(emissor, that.emissor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, emissor);
  }
}
