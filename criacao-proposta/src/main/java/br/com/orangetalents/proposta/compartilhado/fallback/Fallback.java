package br.com.orangetalents.proposta.compartilhado.fallback;

import br.com.orangetalents.proposta.analiseclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analiseclientecartao.controller.AnaliseCliente;
import br.com.orangetalents.proposta.analiseclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.vincularcartaoaproposta.controller.CartaoResource;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Fallback implements AnaliseCliente, CartaoResource {
    @Override
    public AnaliseClienteResponse analiseLiberacaoCartao(@NotNull AnaliseClienteRequest analiseClienteRequest) {
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de análise indisponível no momento");
    }

    @Override
    public CartaoResponse liberaCartao(AnaliseClienteRequest analiseClienteRequest) {
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de cartão indisponível no momento");
    }
}
