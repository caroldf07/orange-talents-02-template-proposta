package br.com.orangetalents.proposta.compartilhado.fallback;

import br.com.orangetalents.proposta.analisarclientecartao.controller.AnaliseCliente;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.avisarviagem.controller.AvisoViagemSistemaExterno;
import br.com.orangetalents.proposta.avisarviagem.view.ViagemCartaoRequest;
import br.com.orangetalents.proposta.avisarviagem.view.ViagemCartaoResponse;
import br.com.orangetalents.proposta.bloquearcartao.controller.BloqueioSistemaExterno;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoResponse;
import br.com.orangetalents.proposta.compartilhado.exceptionhandler.ApiExceptionGenerico;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.controller.CartaoResource;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CartaoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Fallback implements AnaliseCliente, CartaoResource, BloqueioSistemaExterno, AvisoViagemSistemaExterno {

    private final Logger logger = LoggerFactory.getLogger(Fallback.class);
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

        logger.error("Erro no sistema externo");
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de análise indisponível no momento");
    }

    @Override
    public CartaoResponse liberaCartao(AnaliseClienteRequest analiseClienteRequest) {
        logger.error("Erro no sistema externo");
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de cartão indisponível no momento");
    }

    @Override
    public BloqueioCartaoResponse bloqueiaCartao(String id, @NotNull BloqueioCartaoRequest bloqueioCartaoRequest) {
        logger.error("Erro no sistema externo");
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de bloqueio indisponível no momento");
    }

    @Override
    public ViagemCartaoResponse avisoViagem(String id, @NotNull ViagemCartaoRequest viagemCartaoRequest) {
        logger.error("Erro no sistema externo");
        throw new ApiExceptionGenerico(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de aviso de viagem indisponível no momento");
    }
}
