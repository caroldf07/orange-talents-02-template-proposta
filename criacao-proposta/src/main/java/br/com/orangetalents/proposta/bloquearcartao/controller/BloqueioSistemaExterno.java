package br.com.orangetalents.proposta.bloquearcartao.controller;

import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoResponse;
import br.com.orangetalents.proposta.compartilhado.fallback.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

@FeignClient(url = "${sistema.cartao.conta.resource}", name = "cartao-bloqueios", fallback = Fallback.class)
public interface BloqueioSistemaExterno {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios", consumes = "application/json", produces = "application/json")
    BloqueioCartaoResponse bloqueiaCartao(@PathVariable("id") String id, @NotNull BloqueioCartaoRequest bloqueioCartaoRequest);
}
