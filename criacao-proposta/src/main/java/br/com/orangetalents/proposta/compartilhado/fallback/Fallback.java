package br.com.orangetalents.proposta.compartilhado.fallback;

import br.com.orangetalents.proposta.analisarclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analisarclientecartao.controller.AnaliseCliente;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.bloquearcartao.controller.BloqueioSistemaExterno;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoResponse;
import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.controller.CartaoResource;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Fallback implements AnaliseCliente, CartaoResource, BloqueioSistemaExterno {

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

    @Override
    public BloqueioCartaoResponse bloqueiaCartao(String id, @NotNull BloqueioCartaoRequest bloqueioCartaoRequest) {
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de bloqueio indisponível no momento");
    }
}
