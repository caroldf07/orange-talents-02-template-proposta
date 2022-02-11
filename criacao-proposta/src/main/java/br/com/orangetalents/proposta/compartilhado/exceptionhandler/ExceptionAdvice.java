package br.com.orangetalents.proposta.compartilhado.exceptionhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionPadronizado> handle(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    Collection<String> mensagens = new ArrayList<>();
    BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    fieldErrors.forEach(
        fieldError -> {
          String message =
              String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
          mensagens.add(message);
        });

    ExceptionPadronizado exceptionPadronizado = new ExceptionPadronizado(mensagens);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPadronizado);
  }

  @ExceptionHandler(ApiExceptionGenerico.class)
  public ResponseEntity<ExceptionPadronizado> handleApiErroException(
      ApiExceptionGenerico apiExceptionGenerico) {
    Collection<String> mensagens = new ArrayList<>();
    mensagens.add(apiExceptionGenerico.getReason());

    ExceptionPadronizado exceptionPadronizado = new ExceptionPadronizado(mensagens);
    return ResponseEntity.status(apiExceptionGenerico.getHttpStatus()).body(exceptionPadronizado);
  }

  /*
   * Acessar as informações de Endereço da Proposta
   * */
  @InitBinder
  private void initDirectFieldAccess(DataBinder dataBinder) {
    dataBinder.initDirectFieldAccess();
  }
}
