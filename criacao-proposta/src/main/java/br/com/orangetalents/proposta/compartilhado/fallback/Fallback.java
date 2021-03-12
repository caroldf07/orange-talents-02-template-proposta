package br.com.orangetalents.proposta.compartilhado.fallback;

import br.com.orangetalents.proposta.analiseclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analiseclientecartao.controller.AnaliseCliente;
import br.com.orangetalents.proposta.analiseclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criacaoproposta.model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.model.StatusProposta;
import br.com.orangetalents.proposta.criacaoproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.controller.CartaoResource;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Fallback implements AnaliseCliente, CartaoResource {

    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public AnaliseClienteResponse analiseLiberacaoCartao(@NotNull AnaliseClienteRequest analiseClienteRequest) {

        /*
         * Persistindo no banco de dados pois o Fallback quebra o fluxo
         * */
        Proposta proposta = propostaRepository.findByDocumento(analiseClienteRequest.getDocumento());

        proposta.resultadoAnalise(new AnaliseClienteResponse(StatusProposta.NAO_ELEGIVEL));
        propostaRepository.save(proposta);

        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de análise indisponível no momento");
    }

    @Override
    public CartaoResponse liberaCartao(AnaliseClienteRequest analiseClienteRequest) {
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de cartão indisponível no momento");
    }
}
