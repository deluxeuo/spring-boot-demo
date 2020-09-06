package io.platformbuilders.springbootdemo.cliente.excpetion;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.platformbuilders.springbootdemo.cliente.dto.response.ClienteResponseError;

@ControllerAdvice
public class ClienteExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(ClienteNotFoundException.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ClienteResponseError notFoundHandler(ClienteNotFoundException ex) {
    return ClienteResponseError.builder().details(ex.getMessage()).timestamp(LocalDateTime.now()).build();
  }
  
  @ResponseBody
  @ExceptionHandler(NegocioException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ClienteResponseError negocioHandler(NegocioException ex) {
    return ClienteResponseError.builder().details(ex.getMessage()).timestamp(LocalDateTime.now()).build();
  }
}