package br.edu.iftm.rastreamento.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoAcheiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNaoAcheiException(NaoAcheiException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
