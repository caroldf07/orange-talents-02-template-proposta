package br.com.orangetalents.proposta.criarbiometria.model;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime associadaEm = LocalDateTime.now();

    @NotBlank
    private String biometria;

    @ManyToOne
    @Valid
    private Cartao cartao;

    public Biometria(@NotBlank String biometria,
                     @Valid Cartao cartao) {
        this.biometria = Base64.getEncoder().encodeToString(biometria.getBytes(StandardCharsets.UTF_8));
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getBiometria() {
        return biometria;
    }

}
