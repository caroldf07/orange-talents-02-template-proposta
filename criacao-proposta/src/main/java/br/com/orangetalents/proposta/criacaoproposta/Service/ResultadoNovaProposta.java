package br.com.orangetalents.proposta.criacaoproposta.Service;

import br.com.orangetalents.proposta.criacaoproposta.Model.Proposta;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ResultadoNovaProposta {
    private Proposta proposta;
    private Throwable throwable;

    public ResultadoNovaProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public boolean temErro() {
        return throwable != null;
    }

    public Proposta getProposta() {
        Assert.isTrue(!temErro(), "Não deveria chamar caso tenha erro");
        return proposta;
    }

    public Throwable getThrowable() {
        Assert.isTrue(temErro(), "Não deveria chamar caso não tenha erro");
        return throwable;
    }

    /*
    * Criado apenas por conta do Jackson
    * */
    public ResultadoNovaProposta() {
    }
}
