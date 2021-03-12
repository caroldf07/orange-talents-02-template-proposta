package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @Id
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return ativo == bloqueio.ativo && Objects.equals(id, bloqueio.id) && Objects.equals(bloqueadoEm, bloqueio.bloqueadoEm) && Objects.equals(sistemaResponsavel, bloqueio.sistemaResponsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sistemaResponsavel);
    }
}
