package br.com.orangetalents.proposta.analisarclientecartao.controller;

import br.com.orangetalents.proposta.analisarclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.compartilhado.fallback.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

@FeignClient(url = "${sistema.analise.cliente}", name = "analise-cartao", fallback = Fallback.class)
public interface AnaliseCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json", produces = "application/json")
    AnaliseClienteResponse analiseLiberacaoCartao(@NotNull AnaliseClienteRequest analiseClienteRequest);

}
