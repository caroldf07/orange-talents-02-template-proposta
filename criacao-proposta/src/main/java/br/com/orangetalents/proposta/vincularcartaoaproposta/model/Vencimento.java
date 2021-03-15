package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Embeddable
public class Vencimento {

    @Id
    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    /*
     * Criado por conta do hibernate
     * */
    @Deprecated
    public Vencimento() {
    }
}
