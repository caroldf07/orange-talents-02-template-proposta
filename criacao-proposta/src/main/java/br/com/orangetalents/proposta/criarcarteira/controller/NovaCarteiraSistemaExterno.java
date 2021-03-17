package br.com.orangetalents.proposta.criarcarteira.controller;

import br.com.orangetalents.proposta.compartilhado.fallback.Fallback;
import br.com.orangetalents.proposta.criarcarteira.view.NovaCarteiraRequest;
import br.com.orangetalents.proposta.criarcarteira.view.NovaCarteiraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${sistema.cartao.conta.resource}", name = "cartao-carteiras", fallback = Fallback.class)
public interface NovaCarteiraSistemaExterno {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras", consumes = "application/json", produces = "application/json")
    NovaCarteiraResponse novaCarteira(@PathVariable("id") String id, @RequestBody NovaCarteiraRequest novaCarteiraRequest);
}
