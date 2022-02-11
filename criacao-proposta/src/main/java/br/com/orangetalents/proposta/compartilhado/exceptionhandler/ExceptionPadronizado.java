package br.com.orangetalents.proposta.compartilhado.exceptionhandler;

import java.util.Collection;

public class ExceptionPadronizado {

  private Collection<String> mensagens;

  public ExceptionPadronizado(Collection<String> mensagens) {
    this.mensagens = mensagens;
  }

  public Collection<String> getMensagens() {
    return mensagens;
  }
}
