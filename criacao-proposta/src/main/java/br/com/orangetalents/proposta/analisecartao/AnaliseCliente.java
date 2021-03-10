package br.com.orangetalents.proposta.analisecartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:9999", name = "analise-cartao")
public interface AnaliseCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    AnaliseClienteRequest analiseLiberacaoCartao(AnaliseClienteRequest analiseClienteRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/api/solicitacao", produces = "application/json")
    AnaliseClienteResponse analiseLiberacaoCartao(AnaliseClienteResponse analiseClienteResponse);

}
