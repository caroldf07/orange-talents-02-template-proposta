package br.com.orangetalents.proposta.avisarviagem.controller;

import br.com.orangetalents.proposta.avisarviagem.view.ViagemCartaoRequest;
import br.com.orangetalents.proposta.avisarviagem.view.ViagemCartaoResponse;
import br.com.orangetalents.proposta.compartilhado.fallback.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    url = "${sistema.cartao.conta.resource}",
    name = "cartao-viagens",
    fallback = Fallback.class)
public interface AvisoViagemSistemaExterno {
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/cartoes/{id}/avisos",
      consumes = "application/json",
      produces = "application/json")
  ViagemCartaoResponse avisoViagem(
      @PathVariable("id") String id, @RequestBody ViagemCartaoRequest viagemCartaoRequest);
}
