package br.com.orangetalents.proposta.compartilhado.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiExceptionGenerico extends RuntimeException {
  private final HttpStatus httpStatus;

  private final String reason;

  public ApiExceptionGenerico(HttpStatus httpStatus, String reason) {
    super(reason);
    this.httpStatus = httpStatus;
    this.reason = reason;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getReason() {
    return reason;
  }
}
