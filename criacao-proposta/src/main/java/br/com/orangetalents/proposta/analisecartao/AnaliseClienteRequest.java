package br.com.orangetalents.proposta.analisecartao;

import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class AnaliseClienteRequest extends JsonSerializer<Proposta> {
    private String documento;
    private String nome;
    private String idProposta;

    public AnaliseClienteRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    /*
    * Para enviar para sistema externo
    * */
    @Override
    public void serialize(Proposta proposta, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(
                "documento", proposta.getDocumento()
        );
        gen.writeStringField(
                "nome", proposta.getNome()
        );
        gen.writeStringField(
                "idProposta", proposta.getId().toString()
        );
        gen.writeEndObject();

    }

    /*
    * Criado por conta do jackson
    * */
    @Deprecated
    public AnaliseClienteRequest() {
    }
}
