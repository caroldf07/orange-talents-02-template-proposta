package br.com.orangetalents.proposta.compartilhado.cartao;

import br.com.orangetalents.proposta.criarbiometria.view.CartaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Component
public interface SelecionaCartao {

    public ResponseEntity<RedirectView> selecionaCartao(@RequestBody @Valid CartaoRequest cartaoRequest,
                                                        UriComponentsBuilder uriComponentsBuilder);

    public ResponseEntity<?> cartaoSelecionado();
}
