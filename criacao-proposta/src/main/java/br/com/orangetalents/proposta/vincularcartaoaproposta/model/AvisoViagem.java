package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate validoAte;
    private String destino;
    @ManyToOne
    private Cartao cartao;
    private String ipCliente;
    private String userAgent;
    private LocalDateTime instante;

    public AvisoViagem(LocalDate validoAte, String destino, Cartao cartao, String ipCliente, String userAgent, LocalDateTime instante) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.instante = instante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(validoAte, that.validoAte) && Objects.equals(destino, that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validoAte, destino);
    }
}
