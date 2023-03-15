package br.gama.itau.projetofinal.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(DataIntegrityViolationException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na solicitação")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handlerHttpNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na solicitação")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
