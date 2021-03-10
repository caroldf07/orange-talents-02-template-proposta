package br.com.orangetalents.proposta.analisecartao;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class AnaliseClienteResponse extends JsonDeserializer<AnaliseClienteResponse> {
    private String documento;
    private String nome;
    private String idProposta;
    private ResultadoSolicitacao resultadoSolicitacao;

    public AnaliseClienteResponse(String documento, String nome, String idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }


    /*
     * Deserialização do Jackson no retorno do chamado da API externa
     * */
    @Override
    public AnaliseClienteResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        TextNode documento = (TextNode) treeNode.get("documento");
        TextNode nome = (TextNode) treeNode.get("nome");
        TextNode resultadoSolicitacao = (TextNode) treeNode.get("resultadoSolicitacao");
        TextNode idProposta = (TextNode) treeNode.get("idProposta");
        return null;
    }
}
