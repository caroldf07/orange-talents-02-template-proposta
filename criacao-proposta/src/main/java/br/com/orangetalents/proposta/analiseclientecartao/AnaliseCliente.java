package br.com.orangetalents.proposta.analiseclientecartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${sistema.analise.cliente}", name = "analise-cartao")
public interface AnaliseCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json", produces = "application/json")
    AnaliseClienteResponse analiseLiberacaoCartao(AnaliseClienteRequest analiseClienteRequest);

}
