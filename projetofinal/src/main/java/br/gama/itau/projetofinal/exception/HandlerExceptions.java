package br.gama.itau.projetofinal.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(MyDataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handlerDataIntegrityViolationException(MyDataIntegrityViolationException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na solicitação")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(MyNotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na solicitação")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MyNoSuchElementException.class)
    public ResponseEntity<ExceptionDetails> handlerMyNoSuchElementException(MyNoSuchElementException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na busca dos dados")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}

