package br.com.orangetalents.proposta.vincularcartaoaproposta.controller;

import br.com.orangetalents.proposta.analisarclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.compartilhado.fallback.Fallback;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

@FeignClient(url = "${sistema.cartao.conta.resource}", name = "cartao-contas", fallback = Fallback.class)
public interface CartaoResource {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json", produces = "application/json")
    CartaoResponse liberaCartao(@NotNull AnaliseClienteRequest analiseClienteRequest);

}
